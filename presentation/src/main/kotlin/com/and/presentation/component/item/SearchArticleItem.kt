package com.and.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.component.image.BrandProfileImage
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.SearchResultModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Neutral

@Composable
fun SearchArticleItem(
    article: SearchResultModel.SearchedArticleModel,
    onArticleClick: (SearchResultModel.SearchedArticleModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = article.title,
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Strong
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "출연하는 두뇌 서바이벌로,",
            style = Body2Normal,
            fontWeight = FontWeight.Normal,
            color = Caption_Neutral,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BrandProfileImage(
                imageUrl = article.newsLetter.brandName
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = article.newsLetter.brandName,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "2023-11-15",
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Caption_Assistive
            )
        }
    }
}

@Preview(
    name = "ArticleItem Preview",
    showBackground = true
)
@Composable
fun ArticleItemPreview() {
    DefaultWhiteTheme {
        SearchArticleItem(
            article = SearchResultModel.SearchedArticleModel(
                id = 1,
                title = "두뇌 서바이벌",
                matchedText = "출연하는 두뇌 서바이벌로,",
                date = java.time.Instant.now(),
                newsLetter = SearchResultModel.SearchedNewsLetterModel(
                    id = 1,
                    brandName = "뉴스레터 브랜드",
                    imageUrl = null
                ),
                matchType = "title"
            ),
            onArticleClick = {

            }
        )
    }
}