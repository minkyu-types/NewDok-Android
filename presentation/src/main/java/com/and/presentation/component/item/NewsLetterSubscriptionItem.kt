package com.and.presentation.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.InterestCategory
import com.and.presentation.R
import com.and.presentation.component.button.SubscribeButton
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral

@Composable
fun NewsLetterSubscriptionItem(
    newsLetter: NewsLetterModel,
    modifier: Modifier = Modifier
) {
    var isSubscribed by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White
                )
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Line_Neutral,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {

            }

        }
    }
}

@Preview(
    name = "NewsLetterSubscriptionItem Preview",
    showBackground = true
)
@Composable
fun NewsLetterSubscriptionItemPreview() {
    DefaultWhiteTheme {
        NewsLetterSubscriptionItem(
            newsLetter = NewsLetterModel(
                "뉴스레터 브랜드명",
                "",
                "",
                "뉴스레터 간단 소개글은 최대 25자까지 작성할 수 있습니다",
                listOf(
                    InterestCategory.INTEREST_GAME,
                    InterestCategory.INTEREST_CULTURE,
                    InterestCategory.INTEREST_ART_DESIGN,
                )
            )
        )
    }
}