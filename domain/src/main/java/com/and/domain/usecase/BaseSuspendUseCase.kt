package com.and.domain.usecase

fun interface BaseSuspendUseCase<in Parameter, out Result> : BaseUseCase<Parameter, Result> {
    suspend operator fun invoke(parameter: Parameter): Result
}