# NewDok
NewDok의 안드로이드 서비스입니다.

### 개발 환경
- Android Studio Ladybug Feature Drop | 2024.2.2

### 사용 언어
- Kotlin (2.1.10)

### 라이브러리
- Coroutines
- Jetpack Compose
- AndroidX
- Retrofit & OkHttp
- Dagger Hilt
- Coil

## Architecture
- 클린 아키텍처 (멀티 모듈)

## 새 개발 머신 세팅

clone 만으로는 빌드가 되지 않는다. 아래 세 가지는 `.gitignore` 대상이라
저장소에 포함되지 않으므로 기존 개발 머신에서 따로 가져와야 한다.

### 1. `local.properties`

```bash
cp local.properties.example local.properties
```

복사한 뒤 Android SDK 경로, 카카오 네이티브 앱 키, release 서명 정보를 채운다.
각 항목의 의미와 확인 방법은 `local.properties.example` 의 주석 참고.

### 2. `app/google-services.json`

Firebase 콘솔에서 내려받거나 기존 머신에서 복사해 `app/` 에 둔다.
없으면 google-services 플러그인이 빌드를 실패시킨다.

### 3. Release keystore (`.jks`)

signed App Bundle / APK 를 빌드할 때만 필요하다.

기존 업로드 키 파일을 안전한 경로(1Password, 암호화된 USB 등)로 옮긴 뒤
그 절대경로를 `local.properties` 의 `KS_PATH_NEWDOK` 에 지정한다.

> **주의** — Android Studio 의 "Create new..." 로 새 keystore 를 만들면 안 된다.
> 서명키가 바뀌어 Play Console 업로드가 거부되고, 카카오 키 해시와
> Firebase SHA 인증서도 어긋나 릴리즈 빌드에서 카카오 로그인이 깨진다.
> keystore 파일과 비밀번호는 어떤 경우에도 저장소에 커밋하지 않는다.

올바른 키인지 확인하려면 SHA-1 지문을 Play Console 의
설정 > 앱 서명 > "업로드 키 인증서" 와 대조한다.

```bash
keytool -list -v -keystore <keystore 경로>
```

카카오 개발자 콘솔에 등록할 키 해시는 다음과 같이 얻는다.

```bash
keytool -exportcert -alias <alias> -keystore <keystore 경로> \
  | openssl sha1 -binary | openssl base64
```

### 빌드

```bash
./gradlew bundleRelease
```

`gradlew` 도 `.gitignore` 대상이라 clone 직후에는 없다.
Android Studio 에서 프로젝트를 한 번 Sync 하면 생성된다.
