package com.and.domain.model.type

enum class InterestCategory(val id: Int, val value: String) {
    INTEREST_ECONOMY_AFFAIRS(0, "경제·시사"),
    INTEREST_BUSINESS(1, "비즈니스"),
    INTEREST_SCIENCE_TECH(2, "과학·기술"),
    INTEREST_TREND(3, "트렌드"),
    INTEREST_FINANCE(4, "재테크"),
    INTEREST_CONTENTS(5, "콘텐츠"),
    INTEREST_LIFESTYLE(6, "라이프스타일"),
    INTEREST_HOBBY(7, "취미·자기계발"),
    INTEREST_HEALTH(8,  "건강·의학"),
    INTEREST_MENTAL_CARE(9, "멘탈케어"),
    INTEREST_FOOD_DRINK(10, "푸드·드링크"),
    INTEREST_NATURE_ENVIRONMENT(11, "자연·환경"),
    INTEREST_LIVING_INTERIOR(12, "리빙·인테리어"),
    INTEREST_ART_DESIGN(13, "미술·디자인"),
    INTEREST_MUSIC(14, "음악"),
    INTEREST_GAME(15, "게임·스포츠"),
    INTEREST_CONCERT_PERFORMANCE(16, "콘서트·공연"),
    INTEREST_CULTURE(17, "문화"),
    INTEREST_LITERATURE_BOOK(18, "문학·도서"),
    INTEREST_LANGUAGE(19, "언어"),
    INTEREST_MOVIE(20, "영화"),
    INTEREST_TRAVEL(21, "지역·여행"),
    INTEREST_FAMILY(22, "가족"),
    INTEREST_SHOPPING(23, "쇼핑"),
    INTEREST_PET(24, "반려동물"),
    INTEREST_SOCIAL_CONTRIBUTION(25, "사회공헌");

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