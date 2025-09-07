package com.and.presentation.model

import android.os.Parcelable
import com.and.domain.model.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyArticleModel(
    val brandName: String,
    val imageUrl: String?,
    val articleTitle: String,
    val articleId: Int,
    val status: String
): Parcelable {
    fun isRead() = this.status != "Unread"
}

fun Article.toDailyArticleModel() = DailyArticleModel(
    brandName = this.brandName,
    imageUrl = this.imageUrl,
    articleTitle = this.title,
    articleId = this.articleId,
    status = this.status.name
)
