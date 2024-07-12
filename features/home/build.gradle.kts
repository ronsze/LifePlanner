plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "kr.sdbk.home"
}

apply(from = "$rootDir/common.gradle")

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(libs.circleImageView)
}