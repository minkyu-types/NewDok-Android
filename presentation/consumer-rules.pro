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

############################################
# Kakao SDK (공식 권장 + R8 full mode 대응)
# presentation 모듈이 카카오 SDK를 직접 사용하므로 consumer rule로도 전파
############################################
# SDK 모델 클래스의 필드 보존 (Gson 직렬화/역직렬화 대상 — 토큰 저장 포함)
-keep class com.kakao.sdk.**.model.* { <fields>; }
# enum 상수는 Gson EnumTypeAdapter가 Class.getField(이름)으로 리플렉션 조회하므로
# 이름이 바뀌면 NoSuchFieldException(예: "TokenNotFound")으로 크래시 — 이름 보존 필수
-keepclassmembers enum com.kakao.sdk.** { *; }
-keep class * extends com.google.gson.TypeAdapter
# Gson TypeToken 제네릭 시그니처 (R8 full mode에서 소실 방지)
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
