plugins {
    id("com.gradle.enterprise").version("3.15")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "rahulsom.github.io"
