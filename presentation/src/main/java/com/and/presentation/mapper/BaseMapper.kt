package com.and.presentation.mapper

interface BaseMapper<FROM, TO> {
    fun mapToPresentation(input: FROM): TO
    fun mapToDomain(input: TO): FROM
}