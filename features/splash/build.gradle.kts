plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "kr.sdbk.feature_splash"
}

apply(from = "$rootDir/common.gradle")

dependencies {
    implementation(libs.androidx.fragment)
    implementation(libs.circleImageView)
}