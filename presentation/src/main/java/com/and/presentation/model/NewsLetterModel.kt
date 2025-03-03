package com.and.presentation.model

import com.and.presentation.screen.preinvestigation.InterestCategory

data class NewsLetterModel(
    val name: String,
    val profileImage: String,
    val repeatTerm: String,
    val introduction: String,
    val categories: List<InterestCategory>
)
