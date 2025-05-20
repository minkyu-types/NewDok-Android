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
    article: DailyArticleModel,
    onArticleClick: (DailyArticleModel) -> Unit,
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
            text = article.articleTitle,
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
                imageUrl = article.brandName
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = article.brandName,
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
            article = DailyArticleModel(
                "주간 컴퍼니타임스",
                "",
                "신입사원 시절 '최악의 실수'는?",
                1,
                "",
            ),
            onArticleClick = {

            }
        )
    }
}