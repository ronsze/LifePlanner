plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "kr.sdbk.lifeplanner"

    defaultConfig {
        applicationId = "kr.sdbk.lifeplanner"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}

apply(from = "$rootDir/common.gradle")

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":features:splash"))
    implementation(project(":features:home"))
    implementation(project(":features:diary"))
    implementation(project(":features:mypage"))

    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}