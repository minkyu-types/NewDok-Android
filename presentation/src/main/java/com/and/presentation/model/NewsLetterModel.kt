package com.and.presentation.model

import com.and.domain.model.type.InterestCategory

data class NewsLetterModel(
    val name: String,
    val profileImage: String,
    val repeatTerm: String,
    val introduction: String,
    val interests: List<InterestCategory>
)
