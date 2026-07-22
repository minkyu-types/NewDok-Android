# Figma Image Automation

Figma에서 내보낸 이미지를 Android 리소스 폴더(`drawable-*`)로 자동 분류해주는 스크립트입니다.

## 파일 저장 규칙

### 저장 경로

`icon/` 디렉토리 안에 `.png` 파일들을 넣으면 됩니다.

### 파일명 규칙

- **소문자 알파벳, 숫자, 언더스코어(`_`)만** 사용 가능 (대문자, 하이픈 등 불가)
- 확장자는 반드시 **`.png`**

### 해상도별 접미사(postfix)

| 접미사      | 대상 폴더            | 비고         |
| ----------- | -------------------- | ------------ |
| **(없음)**  | `drawable` (mdpi)    | 1x 기본 해상도 |
| **`@1.5x`** | `drawable-hdpi`      | 1.5배        |
| **`@2x`**   | `drawable-xhdpi`     | 2배          |
| **`@3x`**   | `drawable-xxhdpi`    | 3배          |
| **`@4x`**   | `drawable-xxxhdpi`   | 4배          |

### 예시

디자이너에게 `img_home_banner`라는 이미지를 받았다면, `icon/` 폴더에 이렇게 넣으면 됩니다:

```
icon/
├── img_home_banner.png        → drawable/img_home_banner.png (mdpi)
├── img_home_banner@1.5x.png   → drawable-hdpi/img_home_banner.png
├── img_home_banner@2x.png     → drawable-xhdpi/img_home_banner.png
├── img_home_banner@3x.png     → drawable-xxhdpi/img_home_banner.png
└── img_home_banner@4x.png     → drawable-xxxhdpi/img_home_banner.png
```

## 실행 방법

프로젝트 루트 또는 `icon/` 디렉토리에서 실행합니다.

```bash
# 기본: presentation 모듈로 이동
bash icon/icon.sh

# 특정 모듈로 이동하고 싶을 때
bash icon/icon.sh data
```

## 동작 설명

1. `icon/` 폴더 내의 모든 `.png` 파일을 순회합니다.
2. 파일명이 규칙에 맞는지 검사합니다.
3. 접미사(`@1.5x`, `@2x`, `@3x`, `@4x`)를 확인하여 대상 해상도 폴더를 결정합니다.
4. 접미사를 제거한 파일명으로 해당 `drawable-*` 폴더에 **이동(mv)** 시킵니다.
5. 처리 후 `icon/` 폴더에서 png 파일들은 사라지고 각 해상도 폴더에 들어가 있게 됩니다.
