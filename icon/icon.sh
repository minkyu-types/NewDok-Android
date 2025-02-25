#!/bin/bash

# ----------------------------------
# 1. 경로 체크 & 모듈명 설정
# ----------------------------------
CUR_DIR=$(pwd | grep -o '[^/]*$')

if [[ $CUR_DIR != "icon" ]]
then
  if [ -d icon ]
  then
    cd icon
  else
    echo "[Error] 올바른 스크립트 실행 경로가 아닙니다 프로젝트 루트나 icon 디렉토리에서 bash 로 실행시켜 주세요"
    exit 1
  fi
fi

MODULE_NAME=$1
if [[ $MODULE_NAME = "" ]]
then
  MODULE_NAME="presentation"
fi
echo "[Info] 모듈 이름은 $MODULE_NAME 입니다"
MODULE_PATH="../$MODULE_NAME"

# ----------------------------------
# 2. 해상도별 폴더 경로 준비
# ----------------------------------
declare -A DENSITY_PATHS=(
  ["mdpi"]="$MODULE_PATH/src/main/res/drawable"
  ["hdpi"]="$MODULE_PATH/src/main/res/drawable-hdpi"
  ["xhdpi"]="$MODULE_PATH/src/main/res/drawable-xhdpi"
  ["xxhdpi"]="$MODULE_PATH/src/main/res/drawable-xxhdpi"
  ["xxxhdpi"]="$MODULE_PATH/src/main/res/drawable-xxxhdpi"
)

# 폴더가 없으면 생성
for density in "${!DENSITY_PATHS[@]}"; do
  mkdir -p "${DENSITY_PATHS[$density]}" >/dev/null 2>&1
done

# ----------------------------------
# 3. 파일명 검사 정규식
# 소문자 알파벳, 언더스코어, 숫자 + 해상도 + png
# ----------------------------------
VALID_ASSET_REGEX="[a-z0-9_]+(@1.5x|@2x|@3x|@4x)*\.png$"

# 해상도별 접미사 → 폴더 매핑
declare -A SUFFIX_TO_DENSITY=(
  ["@1.5x"]="hdpi"
  ["@2x"]="xhdpi"
  ["@3x"]="xxhdpi"
  ["@4x"]="xxxhdpi"
)

FAILED=false

# ----------------------------------
# 4. png 파일 순회하며 이동
# ----------------------------------
for f in *.png; do
  # *.png 자체를 읽은 경우 건너뛰기
  [[ $f == "*.png" ]] && continue

  # 정규식 검사
  if [[ ! $f =~ $VALID_ASSET_REGEX ]]; then
    echo "[Error] $f 는 올바른 이미지 이름이 아닙니다."
    FAILED=true
    continue
  fi

  # 기본값: mdpi
  targetDensity="mdpi"
  targetSuffix=""

  # @1.5x / @2x / @3x / @4x 중 하나가 있으면 해당 해상도로 변경
  for suffix in "${!SUFFIX_TO_DENSITY[@]}"; do
    if [[ $f == *"$suffix.png" ]]; then
      targetDensity="${SUFFIX_TO_DENSITY[$suffix]}"
      targetSuffix="$suffix"
      break
    fi
  done

  # 실제 옮겨질 파일명 (예: "icon@2x.png" → "icon.png")
  destFileName="${f/%$targetSuffix.png/.png}"

  # 최종 경로
  destPath="${DENSITY_PATHS[$targetDensity]}/$destFileName"

  echo "Moving $f → $destPath"
  mv "$f" "$destPath"
done

# ----------------------------------
# 5. 스크립트 종료
# ----------------------------------
if [ "$FAILED" = true ]; then
  exit 1
fi

echo "[Info] 모든 아이콘이 정상 처리되었습니다."