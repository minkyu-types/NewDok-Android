package com.and.presentation.model

data class RecommendedNewsLettersModel(
    val intersection: List<RecommendedNewsLetterModel>,
    val union: List<RecommendedNewsLetterModel>
)
