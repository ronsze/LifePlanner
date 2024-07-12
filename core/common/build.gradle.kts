plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "kr.sdbk.core_common"
}

apply(from = "../../common.gradle")

dependencies {
    implementation(libs.androidx.fragment)
}