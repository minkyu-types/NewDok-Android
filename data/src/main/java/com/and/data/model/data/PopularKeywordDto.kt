package com.and.data.model.data

import com.and.domain.model.PopularKeyword

data class PopularKeywordDto(
    val rank: Int,
    val keyword: String
)

fun PopularKeywordDto.toDomain() = PopularKeyword(
    rank = this.rank,
    keyword = this.keyword
)