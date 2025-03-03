package com.and.data.mapper.model.response

import com.and.data.mapper.model.data.UserDto

data class GetUserByPhoneNumberResponseDto(
    val data: List<UserDto>
)
