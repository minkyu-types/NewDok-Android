<!--firebender-plan
name: Fix calendar icon visibility
overview: HomeCalendarBar의 달력 아이콘이 보이지 않는 문제를 Icon에 명시적 tint와 size를 지정하여 해결합니다.
todos:
  - id: fix-icon
    content: "HomeCalendarBar Icon에 tint와 size 명시적으로 지정"
-->

# HomeCalendarBar 달력 아이콘 표시 수정

## 문제

[HomeScreen.kt](presentation/src/main/kotlin/com/and/presentation/screen/home/HomeScreen.kt)의 `HomeCalendarBar`에서 `Icon`에 `tint`와 `size`가 명시되지 않아 아이콘이 보이지 않을 수 있음. Material3 `Icon`의 기본 tint는 `LocalContentColor`를 따르는데, 현재 컨텍스트에서 의도하지 않은 색상이 적용될 수 있음.

## 수정 내용

`HomeCalendarBar`의 `Icon`에 명시적으로 tint와 size를 추가:

- **tint**: `Caption_Strong` (날짜 텍스트와 동일한 색상)
- **size**: `24.dp` (적절한 가시성 확보)

변경 위치: 253~256라인의 Icon 컴포저블
