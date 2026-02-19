package com.and.domain.model.type

enum class IndustryCategory(val id: Int, val value: String) {
    DEFAULT(-1, "산업"),
    TECH_IT(1, "테크/IT"),
    BUSINESS_STARTUP(2, "비즈니스/스타트업"),
    MARKETING(3, "마케팅"),
    DESIGN(4, "디자인"),
    LIFESTYLE(5, "라이프스타일"),
    FOOD_COOKING(6, "음식/요리"),
    TRAVEL(7, "여행"),
    CULTURE_ARTS(8, "문화/예술"),
    ECONOMY_FINANCE(9, "경제/금융"),
    EDUCATION(10, "교육"),
    FNB(11, "F&B"),
    DISTRIBUTION_TRADE(12, "유통・무역"),
    CULTURE_ARTS_ENTERTAINMENT(13, "문화・예술・엔터"),
    LIFESTYLE_SERVICES(14, "생활・서비스"),
    IT_GAME_TELECOM(15, "IT・게임・통신"),
    ADVERTISEMENT(16, "광고"),
    ALL_INDUSTRIES(17, "모든 산업"),
    FINANCE_REAL_ESTATE(18, "금융・부동산"),
    MEDIA_PUBLISHING(19, "미디어・출판"),
    ETC(20, "기타"),
    FASHION(21, "패션"),
    PRODUCTION_MANUFACTURING(22, "생산・제조"),
    CONSTRUCTION(23, "건설・건축"),
    STARTUPS_SELF_EMPLOYMENT(24, "창업・자영업"),
    HEALTHCARE(25, "의료");

    companion object {
        fun getStringValues(): List<String> {
            return entries.map { it.value }
        }

        fun getIndustryById(id: Int): IndustryCategory? {
            return entries.find { it.id == id }
        }
    }
}