package com.and.data.model.response

import com.and.data.model.data.UserDto

data class GetUserByPhoneNumberResponseDto(
    val data: List<UserDto>
)
