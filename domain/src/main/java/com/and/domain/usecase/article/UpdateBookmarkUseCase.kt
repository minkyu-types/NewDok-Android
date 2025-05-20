package com.and.domain.usecase.article

import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.UpdateBookmarkUseCase.UpdateBookmarkParams
import javax.inject.Inject

class UpdateBookmarkUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseUseCase<UpdateBookmarkParams, Unit> {

    override suspend fun invoke(parameter: UpdateBookmarkParams) {
        return repository.updateBookmark(
            parameter.articleId
        )
    }

    data class UpdateBookmarkParams(
        val articleId: Int
    )
}
