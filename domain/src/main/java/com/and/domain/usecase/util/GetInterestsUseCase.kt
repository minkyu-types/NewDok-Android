package com.and.domain.usecase.util

import com.and.domain.model.Interest
import com.and.domain.repository.OptionsRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetInterestsUseCase @Inject constructor(
    private val repository: OptionsRepository
) : BaseSuspendUseCase<Unit, List<Interest>> {

    override suspend fun invoke(parameter: Unit): List<Interest> {
        return repository.getInterests()
    }
}
