import io.github.rahulsom.GitAdocCommitDatesSource

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
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val adocCommitDates = providers.of(GitAdocCommitDatesSource::class.java) {
    parameters.contentRoot.set(layout.projectDirectory.dir("src/jbake/content"))
}

jbake {
    adocCommitDates.get().forEach { (k, v) ->
        configuration[k] = v
    }
}