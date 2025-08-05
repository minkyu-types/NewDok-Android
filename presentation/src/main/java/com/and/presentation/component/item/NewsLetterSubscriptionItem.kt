package com.and.presentation.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedPrimaryButton
import com.and.presentation.component.button.OutlinedSecondaryButton
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Line_Neutral

@Composable
fun NewsLetterSubscriptionItem(
    newsLetter: BriefNewsLetterModel,
    modifier: Modifier = Modifier,
    isSubscribeResumed: Boolean,
    subscribeButtonVisible: Boolean = true,
    onSubscribeClick: (BriefNewsLetterModel) -> Unit = { }
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Line_Alternative,
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
                painter = painterResource(id = R.drawable.img_logo),
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
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = newsLetter.brandName,
                    style = Body2Normal,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_line_clock),
                        contentDescription = null,
                        tint = Caption_Assistive,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = newsLetter.publicationCycle,
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Assistive
                    )
                }
            }
            if (subscribeButtonVisible) {
                if (isSubscribeResumed) {
                    OutlinedSecondaryButton(
                        buttonText = stringResource(R.string.subscribe_paused),
                        buttonSize = ButtonSize.SMALL,
                        onClick = {
                            onSubscribeClick(newsLetter)
                        }
                    )
                } else  {
                    OutlinedPrimaryButton(
                        buttonText = stringResource(R.string.subscribe_resume),
                        buttonSize = ButtonSize.SMALL,
                        onClick = {
                            onSubscribeClick(newsLetter)
                        }
                    )
                }
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
            newsLetter = BriefNewsLetterModel(
                0,
                "",
                "평일 아침",
                "뉴스레터 간단 소개글은 최대 25자까지 작성할 수 있습니다",
            ),
            isSubscribeResumed = true,
            onSubscribeClick = {

            }
        )
    }
}