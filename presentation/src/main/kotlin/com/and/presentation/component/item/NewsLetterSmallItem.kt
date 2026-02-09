package com.and.presentation.component.item

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.component.button.SubscribeButton
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.component.image.CommonImage
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.util.removeRippleEffect

@Composable
fun NewsLetterSmallItem(
    newsLetter: NewsLetterModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val displayedIntroduction = if (newsLetter.introduction.length > 25) {
        newsLetter.introduction.take(25) + "..."
    } else {
        newsLetter.introduction
    }

    Card(
        modifier = modifier
            .removeRippleEffect {
                onClick()
            }
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White
                )
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Row {
                CommonImage(
                    imageUrl = newsLetter.profileImageUrl,
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
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = newsLetter.name,
                            style = Body1Normal,
                            fontWeight = FontWeight.Bold,
                            color = Caption_Heavy
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = displayedIntroduction,
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            InterestLazyRow(newsLetter)
        }
    }
}

@Composable
private fun InterestLazyRow(
    newsLetter: NewsLetterModel,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        items(newsLetter.interests) { category ->
            CategoryChip(text = category.value)
        }
    }
}

@Preview(
    name = "BrandSmallItem Preview",
    showBackground = true
)
@Composable
fun BrandSmallItemPreview() {
    DefaultWhiteTheme {

    }
}