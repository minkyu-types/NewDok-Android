package com.and.presentation.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.button.SubscribeButton
import com.and.presentation.model.NewsLetterModel
import com.and.domain.model.type.InterestCategory
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.util.removeRippleEffect

@Composable
fun NewsLetterBigItem(
    newsLetter: NewsLetterModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val displayedIntroduction = if (newsLetter.introduction.length > 40) {
        newsLetter.introduction.take(25) + "..."
    } else {
        newsLetter.introduction
    }
    var isSubscribed by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .removeRippleEffect {
                onClick()
            }
            .width(320.dp)
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                SubscribeButton(
                    isSubscribed = isSubscribed,
                    onSubscribeClick = {
                        // TODO 구독 상태 변경
                        isSubscribed = !isSubscribed
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 20.dp, end = 16.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.height(1.dp),
                color = Line_Neutral
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = newsLetter.name,
                    style = Body1Normal,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = displayedIntroduction,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral,
                    modifier = Modifier
                        .heightIn(min = 40.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    newsLetter.interests.forEach { category ->
                        CategoryChip(text = category.value)
                    }
                }
            }
        }
    }
}

@Preview(
    name = "BrandBigItem Preview",
    showBackground = true
)
@Composable
fun BrandBigItemPreview() {
    DefaultWhiteTheme {
        NewsLetterBigItem(
            newsLetter = NewsLetterModel(
                "뉴스레터 브랜드명",
                "",
                "",
                "뉴스레터 간단 소개글은 최대 40자까지 작성할 수 있습니다.",
                listOf(
                    InterestCategory.INTEREST_GAME,
                    InterestCategory.INTEREST_CULTURE,
                    InterestCategory.INTEREST_ART_DESIGN,
                )
            ),
            onClick = {

            }
        )
    }
}