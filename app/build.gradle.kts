// app/build.gradle.kts
plugins {
    id("com.android.application")  // ✅ No version here!
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tuo.loginpage"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tuo.loginpage"
        minSdk = 21
        targetSdk = 34
    }

    buildFeatures {
        viewBinding = true
    }

    // optionally (recommended)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}