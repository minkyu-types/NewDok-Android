<!--firebender-plan
name: Apply statusBarsPadding globally
overview: MainNavGraph의 루트 NavHost에 statusBarsPadding()을 적용하여 모든 화면에서 상태바 겹침 문제를 일괄 해결합니다.
todos:
  - id: navgraph-padding
    content: "MainNavGraph NavHost에 statusBarsPadding() 추가"
  - id: topbar-fix
    content: "TopBar에 windowInsets 비활성화 및 modifier 파라미터 사용"
-->

# 상태바 패딩 전역 적용

## 접근 방식

[`MainNavGraph.kt`](presentation/src/main/kotlin/com/and/presentation/activity/MainNavGraph.kt)의 루트 `NavHost`에 `Modifier.statusBarsPadding()`을 적용합니다.

- `statusBarsPadding()`은 상태바 inset을 소비(consume)하므로, 하위 Scaffold들(`MainFlowScreen`, `RegisterFlowScreen`, `InvestigationFlowScreen`)이 중복으로 상태바 패딩을 적용하지 않습니다.
- `LoginScreen`, `OnboardingScreen` 등 Scaffold 없이 `Column`만 사용하는 화면들도 자동으로 보호됩니다.

## 변경 파일

### `MainNavGraph.kt` (1곳 수정)

`NavHost`에 `Modifier.statusBarsPadding()` 추가:

```kotlin
NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = Modifier.statusBarsPadding()  // 추가
)
```

### `TopBar.kt` (1곳 수정)

`CenterAlignedTopAppBar`의 modifier에서 외부 `modifier` 파라미터를 사용하도록 수정하고, 내부 windowInsets를 비활성화하여 이중 패딩 방지:

```kotlin
CenterAlignedTopAppBar(
    ...
    windowInsets = WindowInsets(0, 0, 0, 0),  // 추가: 내부 inset 비활성화
    modifier = modifier                        // 변경: 외부 modifier 사용
        .fillMaxWidth()
        .height(56.dp)
        .background(Color.White),
)
```
