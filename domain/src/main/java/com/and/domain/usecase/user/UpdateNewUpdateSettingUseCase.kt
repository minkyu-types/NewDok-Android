package com.and.domain.usecase.user

import com.and.domain.repository.SettingRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class UpdateNewUpdateSettingUseCase @Inject constructor(
    private val repository: SettingRepository
): BaseSuspendUseCase<Unit, Unit> {
    override suspend operator fun invoke(parameter: Unit) {
        repository.toggleNewUpdate()
    }
}