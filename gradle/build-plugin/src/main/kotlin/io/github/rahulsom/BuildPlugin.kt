package io.github.rahulsom

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class BuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val isX86 = System.getProperty("os.arch").lowercase().let {
            it.contains("x86") || it.contains("i386") || it.contains("amd64")
        }

        val dockerBake = project.tasks.register<DockerBakeTask>("dockerBake") {
            group = "jbake"
            description = "Runs jbake within docker"
            srcDir.set(project.layout.projectDirectory.dir("src"))
            configFiles.from(
                project.layout.projectDirectory.file("build.gradle.kts"),
                project.layout.projectDirectory.file("gradle.properties"),
                project.layout.projectDirectory.file("settings.gradle.kts"),
                project.layout.projectDirectory.file("gradle/libs.versions.toml")
            )
            outputDir.set(project.layout.buildDirectory.dir("jbake"))
        }

        project.tasks.register<CreatePostTask>("createPost") {
            group = "jbake"
            description = "Create a new post"
            contentDir.set(project.layout.projectDirectory.dir("src/jbake/content"))
            filename.set(project.providers.gradleProperty("filename"))
        }

        project.tasks.register<PdfTask>("pdf") {
            dependsOn(if (isX86) "bake" else dockerBake)
            htmlFile.set(project.layout.buildDirectory.file("jbake/resume.html"))
            pdfFile.set(project.layout.buildDirectory.file("jbake/resume.pdf"))
            group = "jbake"
            description = "Produce PDF of resume"
        }
    }
}
