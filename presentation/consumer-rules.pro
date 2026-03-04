############################################
# 공통: 애노테이션/제네릭 보존
############################################
-keepattributes *Annotation*, Signature

############################################
# Hilt / Dagger
############################################
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

############################################
# Jetpack Compose
############################################
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

############################################
# Navigation Compose
############################################
-keep class * extends androidx.navigation.Navigator { *; }

############################################
# Coil 3 (이미지 로딩)
############################################
-keep class coil3.** { *; }
-keep class coil3.network.** { *; }
-dontwarn coil3.**

############################################
# OkHttp (Coil이 사용)
############################################
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

############################################
# TedPermission
############################################
-keep class com.gun0912.tedpermission.** { *; }

############################################
# Paging Compose
############################################
-keep class androidx.paging.compose.** { *; }

############################################
# Accompanist (Pager Indicator)
############################################
-keep class com.google.accompanist.** { *; }

############################################
# Coroutines
############################################
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
