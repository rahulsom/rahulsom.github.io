package io.github.rahulsom

import java.time.LocalDate
import java.util.TreeMap
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters

abstract class GitAdocCommitDatesSource : ValueSource<Map<String, String>, GitAdocCommitDatesSource.Parameters> {

    interface Parameters : ValueSourceParameters {
        val contentRoot: DirectoryProperty
    }

    override fun obtain(): Map<String, String> {
        fun commitDateFor(path: String): String {
            return try {
                val process = ProcessBuilder("git", "log", "-1", "--format=%ci", "--", path)
                    .redirectErrorStream(true)
                    .start()
                val output = process.inputStream.bufferedReader().readText().trim()
                process.waitFor()
                output.substringBefore(' ')
            } catch (_: Exception) {
                LocalDate.now().toString()
            }
        }

        val sorted = TreeMap<String, String>()
        parameters.contentRoot.get().asFile.walkTopDown()
            .filter { it.isFile && it.extension == "adoc" }
            .forEach { f ->
                val key = "date_" + f.nameWithoutExtension.replace("-", "_").replace(" ", "_")
                sorted[key] = commitDateFor(f.absolutePath)
            }
        return sorted
    }
}
