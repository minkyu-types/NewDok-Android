############################################
# Moshi (리플렉션 기반 - KotlinJsonAdapterFactory)
############################################
# Kotlin 메타데이터 보존 (리플렉션 어댑터가 참조)
-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*, Signature
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# @Json 어노테이션이 있는 필드 보존
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
}

# Moshi 어댑터 및 JsonAdapter.Factory 보존
-keep class com.squareup.moshi.** { *; }
-keep interface com.squareup.moshi.** { *; }
-keep class * extends com.squareup.moshi.JsonAdapter { *; }

# DTO/Response/Request 모델 클래스 보존 (data 모듈)
-keep class com.and.data.model.** { *; }

# Domain 모델 클래스 보존
-keep class com.and.domain.model.** { *; }

############################################
# Retrofit
############################################
# Retrofit 인터페이스 보존 (동적 프록시 사용)
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# API 인터페이스 전체 보존
-keep interface com.and.data.api.** { *; }

# Retrofit 내부 클래스 보존
-keep class retrofit2.** { *; }
-keepattributes Exceptions

############################################
# OkHttp
############################################
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

############################################
# Hilt / Dagger
############################################
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

############################################
# Coroutines
############################################
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

############################################
# DataStore
############################################
-keep class androidx.datastore.** { *; }

############################################
# Paging3
############################################
-keep class androidx.paging.** { *; }
