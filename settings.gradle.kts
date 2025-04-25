plugins {
    id("com.gradle.develocity").version("4.0.1")
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

rootProject.name = "rahulsom.github.io"
