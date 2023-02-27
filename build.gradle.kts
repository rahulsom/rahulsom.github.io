import org.ajoberstar.grgit.Grgit
import java.time.LocalDate

plugins {
    id("org.jbake.site").version("5.5.0")
    id("org.ajoberstar.git-publish").version("3.0.1")
    id("org.ajoberstar.grgit").version("5.0.0")
    id("groovy")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.groovy:groovy-all:4.+")
    implementation("org.apache.commons:commons-text:1.10.0")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
}

jbake {
    clearCache = true
    asciidoctorjVersion = "1.5.8.1"
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

tasks.named("gitPublishCopy") { dependsOn("bake") }
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