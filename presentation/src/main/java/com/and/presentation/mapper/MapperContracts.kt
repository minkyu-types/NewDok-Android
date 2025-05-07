package com.and.presentation.mapper

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.model.NewsLetterModel

interface NewsLetterMapper: BaseMapper<NewsLetter, NewsLetterModel>
interface BriefNewsLetterMapper: BaseMapper<BriefNewsLetter, BriefNewsLetterModel>