package com.and.data.mapper.api.newsletter

import com.and.data.mapper.model.request.PatchSubscriptionPauseRequestDto
import com.and.data.mapper.model.response.PatchSubscriptionPauseResponseDto
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
        @Body request: PatchSubscriptionPauseRequestDto
    ): Response<PatchSubscriptionPauseResponseDto>
}