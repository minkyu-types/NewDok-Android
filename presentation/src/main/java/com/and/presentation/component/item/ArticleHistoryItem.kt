package com.and.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Neutral

@Composable
fun ArticleHistoryItem(
    article: DailyArticleModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = article.articleTitle,
                style = Body2Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "2023-11-26(금)",
                    style = Label1,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(12.dp)
                        .width(1.dp)
                )
                Text(
                    text = "오전 7:06",
                    style = Label1,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral
                )
            }
        }
    }
}

@Preview(
    name = "NewsLetterHistory Preview",
    showBackground = true
)
@Composable
fun NewsLetterHistoryPreview() {
    DefaultWhiteTheme {
        ArticleHistoryItem(
            article = DailyArticleModel(
                "주간 컴퍼니타임스",
                "",
                "\uD83E\uDD94정원 늘어난다 쭉쭉쭉쭉~?",
                1,
                "",
            )
        )
    }
}