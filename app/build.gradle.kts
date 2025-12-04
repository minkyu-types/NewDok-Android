import java.util.Properties
import java.io.FileInputStream

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "com.and.newdok.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.and.newdok"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val props = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                props.load(FileInputStream(localPropertiesFile))
            }

            val ksPath = props.getProperty("KS_PATH_NEWDOK") ?: ""
            val ksPass = props.getProperty("KS_PASS_NEWDOK") ?: ""
            val keyAlias = props.getProperty("KS_ALIAS_NEWDOK") ?: ""
            val keyPass = props.getProperty("KS_KEY_PASS_NEWDOK") ?: ""

            storeFile = file(ksPath)
            storePassword = ksPass
            this.keyAlias = keyAlias
            keyPassword = keyPass
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            isDebuggable = false
            isJniDebuggable = false

            manifestPlaceholders["cleartextPermitted"] = false

            signingConfig = signingConfigs.getByName("release")
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
