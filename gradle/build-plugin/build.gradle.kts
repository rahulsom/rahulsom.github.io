plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.selenium.java)
}

gradlePlugin {
    plugins {
        create("pdfPlugin") {
            id = "my.pdf"
            implementationClass = "io.github.rahulsom.BuildPlugin"
        }
    }
}
