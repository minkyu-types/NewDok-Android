package com.and.domain.model.type

enum class IndustryCategory(val id: Int, val value: String) {
    DEFAULT(-1, "산업"),
    IT(1, "IT·게임·통신"),
    FnB(2, "F&B"),
    CONSTRUCTION(3, "건설·건축"),
    ADVERTISEMENT(4, "광고"),
    EDUCATION(5, "교육"),
    FINANCE_REAL_ESTATE(6, "금융·부동산"),
    CULTURE_ARTS_ENTERTAINMENT(7, "문화·예술·엔터"),
    MEDIA_PUBLISHING(8, "미디어·출판"),
    PRODUCTION_MANUFACTURING(9, "생산·제조"),
    LIFESTYLE_SERVICES(10, "생활·서비스"),
    DISTRIBUTION_TRADE(11, "유통·무역"),
    HEALTHCARE(12, "의료"),
    FASHION(13, "패션"),
    STARTUPS_SELF_EMPLOYMENT(14, "창업·자영업"),
    YOUR_JOB_IS_GUITAR(15, "기타");

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