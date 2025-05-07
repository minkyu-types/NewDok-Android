package com.and.data.api.newsletter

import com.and.data.model.request.PatchSubscriptionResumeRequestDto
import com.and.data.model.response.PatchSubscriptionResumeResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchSubscriptionResumeApi {

    @PATCH("/newsletters/subscription/resume")
    suspend fun patchSubscriptionResume(
        @Body request: PatchSubscriptionResumeRequestDto
    ): Response<PatchSubscriptionResumeResponseDto>
}