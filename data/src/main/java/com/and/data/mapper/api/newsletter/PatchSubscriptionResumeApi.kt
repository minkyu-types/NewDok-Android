package com.and.data.mapper.api.newsletter

import com.and.data.mapper.model.request.PatchSubscriptionResumeRequestDto
import com.and.data.mapper.model.response.PatchSubscriptionResumeResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchSubscriptionResumeApi {

    @PATCH("/newsletters/subscription/resume")
    fun patchSubscriptionResume(
        @Body request: PatchSubscriptionResumeRequestDto
    ): Response<PatchSubscriptionResumeResponseDto>
}