package com.and.domain.model.type

enum class InterestCategory(val id: Int, val value: String) {
    INTEREST_ALL                  (0,  "전체"),
    INTEREST_ECONOMY_AFFAIRS      (1,  "경제・시사"),
    INTEREST_BUSINESS             (2,  "비즈니스"),
    INTEREST_SCIENCE_TECH         (3,  "과학・기술"),
    INTEREST_TREND                (4,  "트렌드"),
    INTEREST_FINANCE              (5,  "재테크"),
    INTEREST_CONTENTS             (6,  "콘텐츠"),
    INTEREST_LIFESTYLE            (7,  "라이프스타일"),
    INTEREST_HOBBY                (8,  "취미・자기계발"),
    INTEREST_HEALTH               (9,  "건강・의학"),
    INTEREST_MENTAL_CARE          (10, "멘탈케어"),
    INTEREST_FOOD_DRINK           (11, "푸드・드링크"),
    INTEREST_NATURE_ENVIRONMENT   (12, "자연・환경"),
    INTEREST_LIVING_INTERIOR      (13, "리빙・인테리어"),
    INTEREST_ART_DESIGN           (14, "미술・디자인"),
    INTEREST_MUSIC                (15, "음악"),
    INTEREST_GAME                 (16, "게임・스포츠"),
    INTEREST_CONCERT_PERFORMANCE  (17, "콘서트・공연"),
    INTEREST_CULTURE              (18, "문화"),
    INTEREST_LITERATURE_BOOK      (19, "문학・도서"),
    INTEREST_LANGUAGE             (20, "언어"),
    INTEREST_MOVIE                (21, "영화"),
    INTEREST_TRAVEL               (22, "지역・여행"),
    INTEREST_FAMILY               (23, "가족"),
    INTEREST_SHOPPING             (24, "쇼핑"),
    INTEREST_PET                  (25, "반려동물"),
    INTEREST_SOCIAL_CONTRIBUTION  (26, "사회공헌");

    companion object {
        fun getInterestByValue(name: String): InterestCategory {
            return entries.find { it.value == name }
                ?: throw IllegalArgumentException("Wrong interest name: $name")
        }

        fun getInterestById(id: Int): InterestCategory {
            return entries.find { it.id == id }
                ?: throw IllegalArgumentException("Wrong interest id: $id")
        }
    }
}