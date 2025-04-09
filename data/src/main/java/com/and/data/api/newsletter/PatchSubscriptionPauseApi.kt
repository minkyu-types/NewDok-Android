package com.and.data.api.newsletter

import com.and.data.model.request.PatchSubscriptionPauseRequestDto
import com.and.data.model.response.PatchSubscriptionPauseResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

/**
 * 해당 id의 뉴스레터에 대한 사용자의 구독 상태를 "일시 중지"로 변경
 * @author Loki
 */
interface PatchSubscriptionPauseApi {

    @PATCH("/newsletters/subscription/pause")
    fun patchSubscriptionPause(
        @Body request: com.and.data.model.request.PatchSubscriptionPauseRequestDto
    ): Response<com.and.data.model.response.PatchSubscriptionPauseResponseDto>
}