############################################
# 공통: 애노테이션/제네릭 보존
############################################
-keepattributes *Annotation*, Signature

############################################
# Moshi (moshi-kotlin: 리플렉션 기반) 대응
# - 필드명 난독화로 직렬화가 깨지지 않도록 모델 보존
# - Kotlin 메타데이터 보존(리플렉션 어댑터가 참조)
############################################
-keep class kotlin.Metadata { *; }
-keep class com.and.domain.model.** { *; }

############################################
# Retrofit/OkHttp: 불필요 경고 억제
############################################
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

############################################
# Hilt/Dagger: 생성 컴포넌트/매니저 유지(대부분 자동이나 안전망)
############################################
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }

############################################
# DataStore/Paging3: 별도 규칙 불요(SDK가 안전)
############################################
# (규칙 없음)
