import java.time.LocalDate

plugins {
    alias(libs.plugins.jbake)
    alias(libs.plugins.groovy)
    id("my.pdf")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.commons.text)
    implementation(libs.groovy.all)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter)
}


tasks.named("assemble").configure {
    dependsOn("pdf")
}

tasks.named("bake").configure {
    dependsOn("test")
    notCompatibleWithConfigurationCache("Is from a very old plugin")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

fun getResumeCommitDate(): String {
    return try {
        val process = ProcessBuilder("git", "log", "-1", "--format=%ci", "--", "src/jbake/content/resume.adoc")
            .redirectErrorStream(true)
            .start()
        val output = process.inputStream.bufferedReader().readText().trim()
        process.waitFor()
        output.substringBefore(' ')
    } catch (e: Exception) {
        // Fallback to current date if git is not available (e.g., in Docker builds)
        LocalDate.now().toString()
    }
}

jbake {
    version = libs.versions.jbake.core.get()
    configuration["date"] = getResumeCommitDate()
}