plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "kr.sdbk.feature_diary"
}

apply(from = "$rootDir/common.gradle")

dependencies {
}