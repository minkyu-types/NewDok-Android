############################################
# 공통: 애노테이션/제네릭 보존
############################################
-keepattributes *Annotation*, Signature

############################################
# Hilt: 생성 컴포넌트/매니저 유지(안전망)
############################################
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }

############################################
# Navigation(Safe Args)
# - 생성된 NavArgs 클래스는 직접 참조되므로 추가 규칙 불필요
# - 만약 리플렉션으로 NavArgs를 다룬다면 아래 주석 해제
############################################
# -keepclassmembers class ** implements androidx.navigation.NavArgs { *; }

############################################
# Coil/OkHttp: 불필요 경고 억제
############################################
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

############################################
# Jetpack Compose
# - 별도 규칙 불필요(Compose 라이브러리가 자체 규칙 제공)
############################################
# (규칙 없음)
