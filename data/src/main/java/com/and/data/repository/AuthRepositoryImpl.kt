package com.and.data.repository

import com.and.data.api.auth.PostSMSAuthApi
import com.and.data.model.request.PostSMSAuthRequestDto
import com.and.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val postSMSAuthApi: PostSMSAuthApi
): AuthRepository, BaseRepository() {
    override suspend fun requestSMSAuth(phoneNumber: String) {
        return handleApiCall(
            apiCall = {
                postSMSAuthApi.postSMSAuth(
                    PostSMSAuthRequestDto(
                        phoneNumber = phoneNumber
                    )
                )
            },
            mapper = { response ->
                response.code
            }
        )
    }
}