package com.and.domain.model

data class BriefNewsLetter(
    val id: Int,
    val brandName: String,
    /** 서버가 이미지를 주지 않는 뉴스레터가 있어 nullable이다 */
    val imageUrl: String?,
    val publicationCycle: String
)
