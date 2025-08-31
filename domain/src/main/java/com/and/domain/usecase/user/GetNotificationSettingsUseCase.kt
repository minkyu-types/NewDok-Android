package com.and.domain.usecase.user

import com.and.domain.repository.SettingRepository
import com.and.domain.repository.SettingsState
import com.and.domain.usecase.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationSettingsUseCase @Inject constructor(
    private val settingRepository: SettingRepository
): BaseFlowUseCase<Unit, SettingsState> {

    override fun invoke(parameter: Unit): Flow<SettingsState> {
        return settingRepository.settings
    }
}