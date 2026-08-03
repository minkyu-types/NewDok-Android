@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "com.and.newdok.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // 모든 API 인터페이스가 절대경로("/...")를 사용하므로 base URL은 호스트만 의미가 있다
        buildConfigField("String", "BASE_URL_DEV", "\"https://api-dev.newdok.store/\"")
        buildConfigField("String", "BASE_URL_PRODUCT", "\"https://newdok.shop/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(project(":domain"))

    // Coroutines
    implementation(libs.coroutine)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)

    // DataStore
    implementation(libs.datastore.preferences)

    // Firebase Crashlytics (recordException in BaseRepository)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    // Paging3
    implementation(libs.paging.runtime)
    implementation(libs.paging.common)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}