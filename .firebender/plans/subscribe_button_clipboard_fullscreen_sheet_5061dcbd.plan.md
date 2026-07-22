<!--firebender-plan
name: Subscribe button clipboard + fullscreen sheet
overview: 뉴스레터 상세 화면에서 구독하기 버튼 클릭 시 구독 이메일을 클립보드에 복사하는 기능을 추가하고, 바텀시트를 전체 확장으로 변경합니다.
todos:
  - id: fullscreen-sheet
    content: "바텀시트 skipPartiallyExpanded를 true로 변경"
  - id: viewmodel-user-info
    content: "NewsLetterDetailViewModel에 GetUserInfoUseCase 주입 및 subscribeEmail State 추가"
  - id: clipboard-copy
    content: "NewsLetterDetailScreen에서 구독하기 버튼 클릭 시 클립보드 복사 로직 추가"
-->

# 구독하기 버튼 클립보드 복사 + 바텀시트 전체 확장

## 변경 1: 바텀시트 전체 확장

[`NewsLetterDetailScreen.kt`](presentation/src/main/kotlin/com/and/presentation/screen/newsletterdetail/NewsLetterDetailScreen.kt) 97-99행에서 `skipPartiallyExpanded`를 `true`로 변경:

```kotlin
val sheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = true  // false -> true
)
```

## 변경 2: 구독 이메일 클립보드 복사

### 2-1. ViewModel에 유저 정보 로드 추가

[`NewsLetterDetailViewModel.kt`](presentation/src/main/kotlin/com/and/presentation/screen/newsletterdetail/NewsLetterDetailViewModel.kt)에:
- `GetUserInfoUseCase`와 `UserMapper`를 주입
- 유저 정보를 로드하여 `subscribeEmail`을 State로 노출
- `getNewsLetterDetail()` 호출 시 유저 정보도 함께 로드

### 2-2. Screen에서 클립보드 복사 로직 추가

[`NewsLetterDetailScreen.kt`](presentation/src/main/kotlin/com/and/presentation/screen/newsletterdetail/NewsLetterDetailScreen.kt)의 `onSubscribeClick` 핸들러(143-147행)에서:
- `LocalClipboardManager`로 `subscribeEmail`을 클립보드에 복사
- 복사 후 바���시트 표시
- MyPageScreen의 기존 패턴 활용: `clipboardManager.setText(AnnotatedString(email))`
