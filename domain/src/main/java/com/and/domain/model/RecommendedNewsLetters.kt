package com.and.domain.model

data class RecommendedNewsLetters(
    val intersectionNewsLetters: List<RecommendedNewsLetter>,
    val unionNewsLetters: List<RecommendedNewsLetter>
)
