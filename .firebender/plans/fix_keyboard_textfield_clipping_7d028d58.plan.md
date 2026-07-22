<!--firebender-plan
name: Fix keyboard TextField clipping
overview: TextField가 있는 RegisterStep1Screen, RegisterStep4Screen, ProfileEditScreen에 imePadding과 verticalScroll을 추가��여 소프트키보드에 의해 콘텐츠가 잘리는 문제를 해결합니다.
todos:
  - id: register-step1
    content: "RegisterStep1Screen에 imePadding + verticalScroll 추가"
  - id: register-step4
    content: "RegisterStep4Screen에 imePadding + verticalScroll 추가"
  - id: profile-edit
    content: "ProfileEditScreen에 imePadding + verticalScroll 추가"
-->

# 소프트키보드로 인한 TextField 잘림 현상 수정

## 원인 분석

- [AndroidManifest.xml](presentation/src/main/AndroidManifest.xml)에 `android:windowSoftInputMode="adjustResize"`가 설정되어 있어 키보드가 올라오면 레이아웃이 리사이즈됨
- 하지만 TextField가 있는 화면들에 `imePadding()`이나 `verticalScroll()`이 적용되어 있지 않아, 키보드에 의해 콘텐츠가 밀리면서 잘림

## 수정 대상 파일 및 변경 내용

### 1. RegisterStep1Screen (`RegisterStep1Screen.kt`)

- 최상위 Column에 `Modifier.imePadding()` 추가
- 내부 콘텐츠 Column(`.weight(1f)`)에 `verticalScroll(rememberScrollState())` 추가하여 키보드가 올라와도 스크롤 가능하게 함

### 2. RegisterStep4Screen (`RegisterStep4Screen.kt`)

- 최상위 Column에 `Modifier.imePadding()` 추가
- 내부 콘텐츠 Column(`.weight(1f)`)에 `verticalScroll(rememberScrollState())` 추가

### 3. ProfileEditScreen (`ProfileEditScreen.kt`)

- 최상위 Column에 `Modifier.imePadding()` 추가
- Column에 `verticalScroll(rememberScrollState())` 추가하여 스크롤 가능하게 함

## 적용 패턴

각 화면의 최상위 Column modifier에 다음을 추가:

```kotlin
.imePadding()
```

콘텐츠 영역 Column에 다음을 추가:

```kotlin
.verticalScroll(rememberScrollState())
```

필요한 import:
- `androidx.compose.foundation.rememberScrollState`
- `androidx.compose.foundation.verticalScroll`
- `androidx.compose.foundation.layout.imePadding`
