package com.and.domain.usecase.user

import com.and.domain.repository.SettingRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class UpdateNewArticleSettingUseCase @Inject constructor(
    private val repository: SettingRepository
): BaseSuspendUseCase<Unit, Unit> {
    override suspend fun invoke(parameter: Unit) {
        repository.toggleNewArticle()
    }
}