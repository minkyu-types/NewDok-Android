<!--firebender-plan
name: Dropdown dismiss fix
overview: 출생연도 DropdownMenu의 onDismissRequest에 닫기 콜백을 연결하여, 바깥 화면 클릭 시 드롭다운이 닫히도록 수정합니다.
todos:
  - id: fix-dropdown-dismiss
    content: "BirthYearDropDown에 onDismiss 콜백 추가 및 onDismissRequest 연결"
-->

# 출생연도 드롭다운 바깥 클릭 시 닫기 구현

## 변경 대상

[RegisterStep4Screen.kt](presentation/src/main/kotlin/com/and/presentation/screen/register/RegisterStep4Screen.kt)

## 문제

`BirthYearDropDown` 컴포저블의 `DropdownMenu`에서 `onDismissRequest`가 빈 람다(`{}`)로 설정되어 있어, 드롭다운 바깥을 클릭해도 메뉴가 닫히지 않음.

## 수정 내용

1. **`BirthYearDropDown`에 `onDismiss` 파라미터 추가** - 드롭다운이 닫힐 때 호출할 콜백을 받도록 함
2. **`DropdownMenu`의 `onDismissRequest`에 `onDismiss` 연결** - 빈 람다 대신 전달받은 콜백 호출
3. **`RegisterBirthYear`에서 호출 시 `onDismiss` 전달** - `dropdownExpanded = false`를 설정하는 람다 전달

## 핵심 변경 코드

- `BirthYearDropDown` 함수 시그니처에 `onDismiss: () -> Unit` 파라미터 추가
- `onDismissRequest = {}` 를 `onDismissRequest = onDismiss`로 변경
- 호출부에서 `onDismiss = { dropdownExpanded = false }` 전달
