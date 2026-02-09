package com.and.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.presentation.component.image.CommonImage
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.util.removeRippleEffect
import java.time.Instant

@Composable
fun NewsLetterBigItem(
    newsLetter: RecommendedNewsLetterModel,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val displayedIntroduction = if (newsLetter.firstDescription.length > 40) {
        newsLetter.firstDescription.take(25) + "..."
    } else {
        newsLetter.firstDescription
    }

    Card(
        modifier = modifier
            .removeRippleEffect {
                onClick(newsLetter.id)
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
                CommonImage(
                    imageUrl = newsLetter.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
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
                    text = newsLetter.brandName,
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
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    items(newsLetter.interests) { category ->
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
            newsLetter = RecommendedNewsLetterModel(
                0,
                "뉴스레터 브랜드명",
                "뉴스레터 브랜드명",
                "뉴스레터 브랜드명",
                "뉴스레터 브랜드명",
                "뉴스레터 브랜드명",
                "뉴스레터 브랜드명",
                Instant.now(),
                Instant.now(),
                listOf(
                    IndustryCategory.FNB
                ),
                listOf(
                    InterestCategory.INTEREST_GAME,
                    InterestCategory.INTEREST_CULTURE,
                    InterestCategory.INTEREST_ART_DESIGN,
                )
            ),
            onClick = {

            },
        )
    }
}