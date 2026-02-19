package com.and.domain.model.type

enum class InterestCategory(val id: Int, val value: String) {
    INTEREST_ALL                  (0,  "전체"),
    INTEREST_DEVELOPMENT          (1,  "개발"),
    INTEREST_DESIGN               (2,  "디자인"),
    INTEREST_MARKETING            (3,  "마케팅"),
    INTEREST_BUSINESS             (4,  "비즈니스"),
    INTEREST_STARTUP              (5,  "스타트업"),
    INTEREST_FOOD                 (6,  "음식"),
    INTEREST_TRAVEL               (7,  "여행"),
    INTEREST_CULTURE              (8,  "문화"),
    INTEREST_ART                  (9,  "예술"),
    INTEREST_ECONOMY              (10, "경제"),
    INTEREST_FINANCE              (11, "금융"),
    INTEREST_NEWS                 (12, "뉴스"),
    INTEREST_TREND                (13, "트렌드"),
    INTEREST_LIFESTYLE            (14, "라이프스타일"),
    INTEREST_FOOD_DRINK           (15, "푸드・드링크"),
    INTEREST_HEALTH               (16, "건강・의학"),
    INTEREST_MENTAL_CARE          (17, "멘탈케어"),
    INTEREST_ART_DESIGN           (18, "미술・디자인"),
    INTEREST_HOBBY                (19, "취미・자기계발"),
    INTEREST_ECONOMY_AFFAIRS      (20, "경제・시사"),
    INTEREST_INVESTMENT           (21, "재테크"),
    INTEREST_SCIENCE_TECH         (22, "과학・기술"),
    INTEREST_MOVIE                (23, "영화"),
    INTEREST_MUSIC                (24, "음악"),
    INTEREST_LITERATURE_BOOK      (25, "문학・도서"),
    INTEREST_TRAVEL_REGION        (26, "지역・여행"),
    INTEREST_LANGUAGE             (27, "언어"),
    INTEREST_CONTENTS             (28, "콘텐츠"),
    INTEREST_CONCERT_PERFORMANCE  (29, "콘서트・공연"),
    INTEREST_SHOPPING             (30, "쇼핑"),
    INTEREST_PET                  (31, "반려동물"),
    INTEREST_GAME                 (32, "게임・스포츠"),
    INTEREST_NATURE_ENVIRONMENT   (33, "자연・환경"),
    INTEREST_LIVING_INTERIOR      (34, "리빙・인테리어"),
    INTEREST_SOCIAL_CONTRIBUTION  (35, "사회공헌"),
    INTEREST_FAMILY               (36, "가족");

    companion object {
        fun getInterestByValue(name: String): InterestCategory? {
            return entries.find { it.value == name }
        }

        fun getInterestById(id: Int): InterestCategory? {
            return entries.find { it.id == id }
        }
    }
}
