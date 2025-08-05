package com.and.domain.model.type

enum class IndustryCategory(val id: Int, val value: String) {
    DEFAULT(-1, "산업"),
    IT(0, "IT·게임·통신"),
    FnB(1, "F&B"),
    CONSTRUCTION(2, "건설·건축"),
    ADVERTISEMENT(3, "광고"),
    EDUCATION(4, "교육"),
    FINANCE_REAL_ESTATE(5, "금융·부동산"),
    CULTURE_ARTS_ENTERTAINMENT(6, "문화·예술·엔터"),
    MEDIA_PUBLISHING(7, "미디어·출판"),
    PRODUCTION_MANUFACTURING(8, "생산·제조"),
    LIFESTYLE_SERVICES(9, "생활·서비스"),
    DISTRIBUTION_TRADE(10, "유통·무역"),
    HEALTHCARE(11, "의료"),
    FASHION(12, "패션"),
    STARTUPS_SELF_EMPLOYMENT(13, "창업·자영업"),
    YOUR_JOB_IS_GUITAR(14, "기타");

    companion object {
        fun getStringValues(): List<String> {
            return entries.map { it.value }
        }

        fun getIndustryById(id: Int): IndustryCategory {
            return entries.find { it.id == id }
                ?: throw IllegalArgumentException("Wrong industry id $id")
        }
    }
}