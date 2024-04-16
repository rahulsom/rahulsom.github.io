import org.ajoberstar.grgit.Grgit
import org.jsoup.Jsoup
import org.xhtmlrenderer.pdf.ITextRenderer
import java.time.LocalDate

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jsoup:jsoup:1.17.2")
        classpath("org.xhtmlrenderer:flying-saucer-pdf:9.7.2")
    }
}

plugins {
    id("org.jbake.site").version("5.5.0")
    id("org.ajoberstar.git-publish").version("4.2.2")
    id("org.ajoberstar.grgit").version("5.2.2")
    id("groovy")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.groovy:groovy-all:4.+")
    implementation("org.apache.commons:commons-text:1.12.0")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
}

gitPublish {
    val grgit = Grgit.open(mapOf("currentDir" to project.rootDir))
    repoUri.set(grgit.remote.list().first().url)
    branch.set("gh-pages")
    contents {
        from(file("build/jbake")) {
            into(".")
        }
    }
}

tasks.register("pdf") {
    dependsOn("bake")
    inputs.file(File("${projectDir}/build/jbake/resume.html"))
    outputs.file(File("${projectDir}/build/jbake/resume.pdf"))
    doLast {
        val html = File("${projectDir}/build/jbake/resume.html")
        val pdf = File("${projectDir}/build/jbake/resume.pdf")

        val document = Jsoup.parse(html, "UTF-8").also {
            it.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml)
            it.setBaseUri(html.parentFile.toURI().toString())
        }
        val renderer = ITextRenderer()
        renderer.sharedContext.also {
            it.isPrint = true
            it.isInteractive = false
        }
        val baseUri = html.parentFile.toURI().toString()
        println("Base URI: $baseUri")
        renderer.setDocumentFromString(document.html(), baseUri)
        renderer.layout()
        renderer.createPDF(pdf.outputStream())
    }
}

tasks.named("gitPublishCopy") { dependsOn("bake", "pdf") }
tasks.named("bake") { dependsOn("test") }

tasks.register("publish") { dependsOn("gitPublishPush") }

tasks.register("createPost") {
    doLast {
        val filename = if (project.hasProperty("filename")) {
            project.property("filename") as String
        } else {
            "new-post"
        }
        val dir = "src/jbake/content/blog/${LocalDate.now().year}"
        if (!File(dir).exists()) {
            File(dir).mkdirs()
        }
        val path = "${dir}/${filename}.adoc"

        File(path).writeText(
            listOf(
                "= $filename",
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
        println(filename)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}