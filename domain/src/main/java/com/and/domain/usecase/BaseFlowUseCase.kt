package com.and.domain.usecase

import kotlinx.coroutines.flow.Flow

fun interface BaseFlowUseCase<in Parameter, out Result> : BaseUseCase<Parameter, Result> {
    operator fun invoke(parameter: Parameter): Flow<Result>
}