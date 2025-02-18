@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.and.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    // Social login
//    implementation(libs.kakao.login)
//    implementation(libs.naver.login)

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.livedata)
    implementation(libs.hilt.navigation)

    // Coroutine
    implementation(libs.coroutine)

    // Hilt
    implementation(libs.hilt)
    implementation(libs.constraintlayout)
    ksp(libs.hilt.compiler)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)

    // Jetpack
    implementation(libs.paging.runtime)
    implementation(libs.paging.common)

    // Glide
    implementation(libs.glide)

    // Ted permission
    implementation(libs.ted.permission)

    // Ktx
    implementation(libs.ktx.activity)
    implementation(libs.ktx.fragment)
    implementation(libs.ktx.viewmodel)
    implementation(libs.ktx.runtime)
    implementation(libs.ktx.preference)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}