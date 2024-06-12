plugins {
    id("com.gradle.develocity").version("3.17.5")
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

rootProject.name = "rahulsom.github.io"
