import java.util.Properties
import java.io.FileInputStream

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.appdistribution)
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
        versionCode = 10
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Kakao Native App Key from local.properties
        val props = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            props.load(FileInputStream(localPropertiesFile))
        }
        val kakaoNativeAppKey = props.getProperty("KAKAO_NATIVE_APP_KEY") ?: ""
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"$kakaoNativeAppKey\"")
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = kakaoNativeAppKey
    }

    signingConfigs {
        // release 빌드 태스크일 때만 signing config 생성
        val isReleaseBuild = gradle.startParameter.taskNames.any {
            it.contains("release", ignoreCase = true)
        }

        if (isReleaseBuild) {
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
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false

            isDebuggable = true
            isJniDebuggable = true

            manifestPlaceholders["cleartextPermitted"] = true
        }

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

            // signing config가 생성된 경우에만 설정
            signingConfigs.findByName("release")?.let {
                signingConfig = it
            }

            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }
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

// Firebase App Distribution — 테스터에게 빌드 직접 배포
// 사용법: ./gradlew assembleRelease appDistributionUploadRelease
//        (debug 배포는 assembleDebug appDistributionUploadDebug)
// 인증: firebase login(로컬) 또는 FIREBASE_SERVICE_CREDENTIALS_FILE 환경변수(CI)에
//       서비스 계정 JSON 경로 지정. appId는 google-services.json에서 자동 인식.
firebaseAppDistribution {
    artifactType = "APK"
    // Firebase 콘솔 > App Distribution > 테스터 및 그룹에서 만든 그룹 별칭
    groups = "testers"
    // 배포마다 내용을 바꾸려면 이 파일을 수정하거나 태스크 실행 전 덮어쓰면 된다
    releaseNotesFile = rootProject.file("distribution-release-notes.txt").absolutePath

    System.getenv("FIREBASE_SERVICE_CREDENTIALS_FILE")?.let {
        serviceCredentialsFile = it
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    // Kakao SDK
    implementation(libs.kakao.login)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
