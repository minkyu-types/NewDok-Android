package com.and.data.mapper

interface BaseMapper<FROM, TO> {
    fun mapToData(input: FROM): TO
    fun mapToDomain(input: TO): FROM
}