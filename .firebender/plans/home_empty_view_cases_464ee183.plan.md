<!--firebender-plan
name: Home empty view cases
overview: HomeScreen에서 homeArticles가 비어있을 때, 비회원/구독 없음/아티클 없음 3가지 케이스를 구분하여 각각 다른 EmptyView를 보여주도록 구현합니다.
todos:
  - id: sealed-class
    content: "HomeEmptyType sealed class 정의"
  - id: viewmodel
    content: "HomeViewModel에 IsGuestModeUseCase, GetSubscribedNewsLettersCountUseCase 주입 및 상태 추가"
  - id: screen-logic
    content: "HomeScreen에서 3가지 케이스 분기 로직 구현"
  - id: empty-view
    content: "HomeScreenEmptyView UI 구현 (3가지 케이스별 레이아웃)"
  - id: strings
    content: "strings.xml에 케이스별 문자열 리소스 추가"
-->

# HomeScreen EmptyView 3가지 케이스 분기 구현

## 현재 상태
- `HomeScreen`에서 `homeArticles.isEmpty()` 일 때 빈 `HomeScreenEmptyView`를 보여주고 있음
- 비회원 여부: `IsGuestModeUseCase` (Flow<Boolean>) 이미 존재
- 구독 뉴스레터 수: `GetSubscribedNewsLettersCountUseCase` 이미 존재
- 버튼 컴포넌트: `SolidPrimaryButton` 이미 존재
- 이미지 리소스: `img_home_empty_article`, `img_empty_newsletter`, `img_empty_article_nonmember` 존재

## 3가지 케이스 상세

### 케이스1: 오늘 아티클 없음 (회원 + 구독 있음 + 아티클 비어있음)
- **이미지**: `img_home_empty_article`
- **텍스트1**: "오늘 도착한 아티클이 없어요."
- **텍스트2**: "구독 신청 이후 수신된 아티클만 볼 수 있어요."
- **버튼**: "발행되는 뉴스레터 보기" -> 콜백 전달 (`onViewNewsLettersClick`)

### 케이스2: 구독 없음 (회원 + 구독 없음)
- **이미지**: `img_empty_newsletter`
- **텍스트1**: "구독 중인 뉴스레터가 없어요."
- **텍스트2**: "뉴스레터를 구독하면\n발행일에 맞춰 여기로 배달드려요."
- **버튼**: "내게 필요한 뉴스레터 추천받기" -> 콜백 전달 (`onRecommendClick`)

### 케이스3: 비회원
- **이미지**: `img_empty_article_nonmember`
- **텍스트1**: "회원이 되면 뉴스레터를\n간편하게 모아볼 수 있어요!"
- **텍스트2**: 없음
- **버튼**: "회원가입" -> 콜백 전달 (`onSignUpClick`)
- **버튼 아래 Row**: "이미 계정이 있나요?" (클릭 불가) + "로그인" (밑줄, 클릭 가능 -> `onLoginClick`)

## 변경 사항

### 1. HomeEmptyType sealed class 추가
[HomeScreen.kt](presentation/src/main/kotlin/com/and/presentation/screen/home/HomeScreen.kt)에 정의:

```kotlin
sealed class HomeEmptyType {
    data object Guest : HomeEmptyType()
    data object NoSubscription : HomeEmptyType()
    data object NoArticleToday : HomeEmptyType()
}
```

### 2. HomeViewModel에 비회원/구독 상태 추가
[HomeViewModel.kt](presentation/src/main/kotlin/com/and/presentation/screen/home/HomeViewModel.kt)에:
- `IsGuestModeUseCase`, `GetSubscribedNewsLettersCountUseCase` 주입
- `isGuestMode: State<Boolean>` 상태 (init에서 Flow collect)
- `subscribedCount: State<Int>` 상태 (init에서 API 호출)

### 3. HomeScreen 분기 로직 수정
`homeArticles`가 비어있을 때 우선순위 분기:
1. `isGuestMode == true` -> `HomeEmptyType.Guest`
2. `subscribedCount == 0` -> `HomeEmptyType.NoSubscription`
3. 그 외 -> `HomeEmptyType.NoArticleToday`

### 4. HomeScreenEmptyView 구현
공통 레이아웃: Column(horizontalAlignment = CenterHorizontally)
- Image (케이스별 다른 drawable)
- Text1 (케이스별 다른 텍스트)
- Text2 (케이스3에서는 없음)
- SolidPrimaryButton (케이스별 다른 텍스트, 콜백)
- 케이스3 전용: Row("이미 계정이 있나요?" + 밑줄 "로그인" 클릭 텍스트)

### 5. HomeScreen 콜백 추가
`HomeScreen`에 새 콜백 파라미터 추가:
- `onViewNewsLettersClick`
- `onRecommendClick`
- `onSignUpClick`
- `onLoginClick`

### 6. strings.xml 문자열 추가
[strings.xml](presentation/src/main/res/values/strings.xml)에 케이스별 텍스트1, 텍스트2, 버튼 문자열 추가
