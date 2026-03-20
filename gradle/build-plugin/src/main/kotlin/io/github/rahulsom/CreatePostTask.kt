package io.github.rahulsom

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.time.LocalDate

abstract class CreatePostTask : DefaultTask() {
    @get:Input
    @get:Optional
    abstract val filename: Property<String>

    @get:OutputDirectory
    abstract val contentDir: DirectoryProperty

    @TaskAction
    fun create() {
        val name = filename.getOrElse("new-post")
        val year = LocalDate.now().year
        val dir = contentDir.get().asFile.resolve("blog/$year")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = dir.resolve("${name}.adoc")
        file.writeText(
            listOf(
                "= $name",
                "Rahul Somasunderam",
                "${LocalDate.now()}",
                ":jbake-type: post",
                ":jbake-status: published",
                ":jbake-tags: fitness, tracker, bodybugg, fitbit, fuelband, basis",
                ":idprefix:",
                "",
                "subtitle",
                "",
                "body"
            ).joinToString("\n")
        )
        println(name)
    }
}
