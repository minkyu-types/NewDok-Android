package com.and.domain.usecase.util

import com.and.domain.model.Industry
import com.and.domain.repository.OptionsRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetIndustriesUseCase @Inject constructor(
    private val repository: OptionsRepository
) : BaseSuspendUseCase<Unit, List<Industry>> {

    override suspend fun invoke(parameter: Unit): List<Industry> {
        return repository.getIndustries()
    }
}
