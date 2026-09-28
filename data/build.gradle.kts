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

        // 모든 API 인터페이스는 상대경로를 사용하므로 base URL 뒤에 그대로 이어붙는다.
        // dev 서버는 /api prefix를 사용하고 운영 서버는 루트에서 서빙하므로
        // prefix 차이는 여기(base URL)에서만 관리한다. 반드시 '/'로 끝나야 한다.
        buildConfigField("String", "BASE_URL_DEV", "\"https://api-dev.newdok.store/\"")
        buildConfigField("String", "BASE_URL_PRODUCT", "\"https://api.newdok.store/\"")
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