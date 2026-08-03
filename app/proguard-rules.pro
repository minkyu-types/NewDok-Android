############################################
# 공통: 디버깅을 위한 소스 정보 보존
############################################
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-keepattributes *Annotation*, Signature, Exceptions
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

############################################
# Kotlin
############################################
-keep class kotlin.Metadata { *; }
-keep class kotlin.reflect.** { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

############################################
# Hilt / Dagger
############################################
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }
-keep class * { @dagger.hilt.android.AndroidEntryPoint *; }
-keep @dagger.hilt.android.AndroidEntryPoint class * { *; }
-keep @dagger.hilt.InstallIn class * { *; }
-keep @dagger.Module class * { *; }
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }

############################################
# Moshi (리플렉션 기반)
############################################
-keep class com.squareup.moshi.** { *; }
-keep interface com.squareup.moshi.** { *; }
-keep class * extends com.squareup.moshi.JsonAdapter { *; }
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
}

# 어노테이션 메서드 방식 커스텀 어댑터(@FromJson/@ToJson)는
# Moshi가 리플렉션으로만 호출하므로 R8 full mode에서 제거되지 않도록 보존
# (제거되면 Moshi.Builder().add() 시점에 IllegalArgumentException으로 크래시)
-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}

# DTO 모델 보존 (직렬화/역직렬화 대상)
-keep class com.and.data.model.** { *; }
-keep class com.and.domain.model.** { *; }

############################################
# Retrofit
############################################
-keep class retrofit2.** { *; }
-keep interface com.and.data.api.** { *; }
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

############################################
# OkHttp
############################################
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

############################################
# Coroutines
############################################
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

############################################
# Jetpack Compose
############################################
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

############################################
# Navigation
############################################
-keep class * extends androidx.navigation.Navigator { *; }

############################################
# Coil 3
############################################
-keep class coil3.** { *; }
-dontwarn coil3.**

############################################
# DataStore
############################################
-keep class androidx.datastore.** { *; }

############################################
# Paging3
############################################
-keep class androidx.paging.** { *; }

############################################
# TedPermission
############################################
-keep class com.gun0912.tedpermission.** { *; }

############################################
# Accompanist
############################################
-keep class com.google.accompanist.** { *; }

############################################
# SplashScreen
############################################
-keep class androidx.core.splashscreen.** { *; }

############################################
# Android 기본
############################################
-keep class * extends android.app.Application { *; }
-keep class * extends android.app.Activity { *; }
-keep class * extends android.app.Service { *; }
-keep class * extends android.content.BroadcastReceiver { *; }
-keep class * extends android.content.ContentProvider { *; }
