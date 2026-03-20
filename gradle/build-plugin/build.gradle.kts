plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.22.1")
    implementation("org.xhtmlrenderer:flying-saucer-pdf:10.1.0")
}

gradlePlugin {
    plugins {
        create("pdfPlugin") {
            id = "my.pdf"
            implementationClass = "io.github.rahulsom.BuildPlugin"
        }
    }
}
