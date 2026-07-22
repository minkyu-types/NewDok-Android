<!--firebender-plan
name: Article detail navigation refactor
overview: ArticleDetailScreen 네비게이션을 articleId 기반 route로 전환하고, 모든 아티클 클릭 진입점에서 ArticleDetail로 이동하도록 연결합니다.
todos:
  - id: route-refactor
    content: "MainFlowScreen의 ArticleDetail route를 articleId 기반으로 전환"
  - id: screen-refactor
    content: "ArticleDetailScreen을 articleId 파라미터 기반으로 리팩토링"
  - id: home-entry
    content: "HomeScreen 진입점을 articleId 전달 방식으로 전환"
  - id: bookmark-entry
    content: "BookmarkArticleItem에 clickable 추가 및 ArticleDetail 네비게이션 연결"
  - id: search-entry
    content: "SearchArticleItem에 clickable 추가 및 ArticleDetail 네비게이션 연결"
  - id: alarm-entry
    content: "AlarmScreen 아티클 클릭 시 ArticleDetail 네비게이션 연결"
  - id: newsletter-entry
    content: "ArticleHistoryItem에 클릭 콜백 추가 및 ArticleDetail 네비게이션 연결"
-->

# ArticleDetail 네비게이션 전환 및 진입점 연결

## 현재 상태

- `ArticleDetail` route는 `DailyArticleModel`을 `savedStateHandle`로 전달받는 방식
- 실제 동작하는 진입점은 **HomeScreen 1곳뿐**
- BookmarkScreen, SearchScreen, AlarmScreen, NewsLetterDetailScreen은 클릭 핸들러가 미구현이거나 빈 람다

## 변경 1: Route 전환 (articleId 기반)

[`MainFlowScreen.kt`](presentation/src/main/kotlin/com/and/presentation/activity/MainFlowScreen.kt)에서:

- route를 `ArticleDetail/{articleId}`로 변경
- `savedStateHandle` 대신 `navArgument`로 `articleId: Int`를 전달
- `ArticleDetailScreen`에 `articleId`만 전달, ViewModel이 모든 데이터를 API로 조회

## 변경 2: ArticleDetailScreen 파라미터 변경

[`ArticleDetailScreen.kt`](presentation/src/main/kotlin/com/and/presentation/screen/articledetail/ArticleDetailScreen.kt)에서:

- `article: DailyArticleModel` 파라미터를 `articleId: Int`로 변경
- TopBar 타이틀, ArticleCard 등을 ViewModel의 `ArticleDetail` 데이터 기반으로 표시

## 변경 3: 진입점 연결

각 화면의 `onArticleClick` 콜백을 `(Int) -> Unit` (articleId)으로 통일하고, `navController.navigate("ArticleDetail/$articleId")` 호출:

- **HomeScreen** (`HomeScreen.kt`): 이미 동작 중, articleId 전달 방식으로 전환
- **BookmarkScreen** (`BookmarkScreen.kt` / `BookmarkArticleItem.kt`): `BookmarkArticleItem`에 clickable 추가, articleId 전달
- **AlarmScreen** (`AlarmScreen.kt`): 알림 아이템 클릭 시 articleId 전달 (현재 미구현 부분 연결)
- **SearchScreen** (`SearchScreen.kt` / `SearchArticleItem.kt`): `SearchArticleItem`에 clickable 추가, articleId 전달
- **NewsLetterDetailScreen** (`NewsLetterDetailScreen.kt` / `ArticleHistoryItem.kt`): `ArticleHistoryItem`에 클릭 콜백 추가, articleId 전달
