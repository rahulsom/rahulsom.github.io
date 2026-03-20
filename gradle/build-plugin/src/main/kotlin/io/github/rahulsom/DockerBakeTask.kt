package io.github.rahulsom

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.ProjectLayout
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import javax.inject.Inject

abstract class DockerBakeTask @Inject constructor(
    private val execOperations: ExecOperations,
    private val layout: ProjectLayout
) : DefaultTask() {

    @get:InputDirectory
    abstract val srcDir: DirectoryProperty

    @get:InputFiles
    abstract val configFiles: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun bake() {
        execOperations.exec {
            commandLine(
                "docker", "run",
                "--rm",
                "-v", "${System.getProperty("user.home")}/.gradle:/root/.gradle",
                "-v", "${layout.projectDirectory.asFile.canonicalPath}:/workspace",
                "-w", "/workspace",
                "azul/zulu-openjdk:21-latest",
                "./gradlew", "bake", "--no-configuration-cache"
            )
        }
    }
}
