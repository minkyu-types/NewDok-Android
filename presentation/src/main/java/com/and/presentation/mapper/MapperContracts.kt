package com.and.presentation.mapper

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.model.RecommendedNewsLetterModel

interface NewsLetterMapper: BaseMapper<NewsLetter, NewsLetterModel>
interface RecommendedNewsLetterMapper: BaseMapper<RecommendedNewsLetter, RecommendedNewsLetterModel>
interface BriefNewsLetterMapper: BaseMapper<BriefNewsLetter, BriefNewsLetterModel>