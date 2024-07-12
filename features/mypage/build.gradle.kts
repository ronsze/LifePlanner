plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "kr.sdbk.feature_mypage"
}

apply(from = "../../common.gradle")

dependencies {
    implementation(libs.circleImageView)
}