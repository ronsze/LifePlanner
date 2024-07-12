plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "kr.sdbk.data"
}

apply(from = "$rootDir/common.gradle")

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}