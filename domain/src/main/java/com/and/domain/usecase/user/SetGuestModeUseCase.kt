package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class SetGuestModeUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseSuspendUseCase<Boolean, Unit> {

    override suspend fun invoke(parameter: Boolean) {
        repository.setGuestMode(parameter)
    }
}
