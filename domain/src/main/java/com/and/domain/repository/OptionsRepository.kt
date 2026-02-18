package com.and.domain.repository

import com.and.domain.model.Industry
import com.and.domain.model.Interest

interface OptionsRepository {
    suspend fun getIndustries(): List<Industry>
    suspend fun getInterests(): List<Interest>
}
