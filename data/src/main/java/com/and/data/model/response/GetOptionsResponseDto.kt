package com.and.data.model.response

import com.and.data.model.data.DayDto
import com.and.data.model.data.IndustryDto
import com.and.data.model.data.InterestDto

data class GetOptionsResponseDto(
    val industries: List<IndustryDto>,
    val interests: List<InterestDto>,
    val days: List<DayDto>
)
