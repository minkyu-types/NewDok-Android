package com.and.presentation.screen.preinvestigation

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.InterestCategory
import com.and.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.item.CategoryChip
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Neutral90
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun InvestigationStep3Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var bottomSheetOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.pre_investigation_curation_title, "%닉네임%"),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.pre_investigation_curation_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            InvestigationNewLetterList(
                listOf(
                    NewsLetterModel(
                        "NEWNEEK",
                        "프로필 이미지",
                        "매주 평일 아침",
                        "세상 돌아가는 소식, 뉴닉으로!",
                        listOf(
                            InterestCategory.INTEREST_GAME,
                            InterestCategory.INTEREST_CULTURE,
                            InterestCategory.INTEREST_ART_DESIGN,
                        ),
                    ),
                    NewsLetterModel(
                        "오렌지레터",
                        "프로필 이미지",
                        "매주 수요일",
                        "다양한 삶의 관점을 배우며 함께 성장하는 디자인 커뮤니티",
                        listOf(
                            InterestCategory.INTEREST_ECONOMY_AFFAIRS,
                            InterestCategory.INTEREST_BUSINESS,
                            InterestCategory.INTEREST_TREND,
                        ),
                    )
                ),
                onSubscribeClick = {
                    onSubscribeClick()
                    // 사용자 구독용 이메일을 클립보드에 복사한 후
                    // 바텀시트 열기
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 24.dp)
            )
            ConditionalNextButton(
                enabled = true,
                onClick = { onNext() },
                modifier = Modifier
                    .padding(bottom = 24.dp),
                buttonText = stringResource(R.string.move_to_main)
            )
        }
    }
}

@Composable
fun InvestigationNewLetterList(
    newsLetters: List<NewsLetterModel>,
    onSubscribeClick: (NewsLetterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(newsLetters) { newsLetter ->
            InvestigationNewsLetterItem(
                newsLetter = newsLetter,
                onSubscribeClick = { onSubscribeClick(it) }
            )
        }
    }
}

@Composable
fun InvestigationNewsLetterItem(
    newsLetter: NewsLetterModel,
    onSubscribeClick: (NewsLetterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
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
                .background(Neutral90)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Line_Neutral,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = newsLetter.name,
                        style = Body2Normal,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Heavy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_line_clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = newsLetter.repeatTerm,
                            style = Label1,
                            fontWeight = FontWeight.Medium,
                            color = Caption_Assistive
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .removeRippleEffect { onSubscribeClick(newsLetter) }
                        .border(
                            width = 1.dp,
                            color = Primary_Normal,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.subscribe),
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Primary_Normal
                    )
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Line_Neutral
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = newsLetter.introduction,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Strong
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                newsLetter.categories.forEach { category ->
                    CategoryChip(text = category.value)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(
    name = "InvestigationStep3Screen Preview",
    showBackground = true
)
@Composable
fun InvestigationStep3ScreenPreview() {
    DefaultWhiteTheme {
        InvestigationStep3Screen(
            onNext = {

            },
            onBack = {

            },
            onSubscribeClick = {

            }
        )
    }
}