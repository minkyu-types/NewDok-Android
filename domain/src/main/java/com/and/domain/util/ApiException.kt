package com.and.domain.util

import java.lang.Exception

class ApiException(
    val statusCode: Int,
    override val message: String?
): Exception(message)