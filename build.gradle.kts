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


tasks.named("assemble") {
    dependsOn("pdf")
}

tasks.named("bake") { dependsOn("test") }


tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

jbake {
    version = libs.versions.jbake.core.get()
}