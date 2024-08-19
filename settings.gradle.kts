plugins {
    id("com.gradle.develocity").version("3.18")
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

rootProject.name = "rahulsom.github.io"
