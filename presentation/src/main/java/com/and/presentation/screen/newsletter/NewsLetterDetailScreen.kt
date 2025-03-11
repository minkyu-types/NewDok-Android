package com.and.presentation.screen.newsletter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.component.item.InterestTag
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import com.and.presentation.component.item.ArticleHistoryItem
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Body2Reading
import com.and.presentation.ui.Gray700
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsLetterDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(1000L)
                isRefreshing = false
            }
        }
    )
    val grayAreaHeightDp = (70.dp * pullRefreshState.progress).coerceAtMost(70.dp)

    val newsLetter = NewsLetterModel(
        "뉴스레터 브랜드명",
        "",
        "뉴스레터 발송일",
        "세상 돌아가는 소식은 궁금한데, 시간이 없다고요? <뉴닉>은 신문 볼 새 없이 바쁘지만, 세상과의 연결고리는 튼튼하게 유지하고 싶은 여러분들을 위해 세상 돌아가는 소식을 모두 담아 간단하게 정리해드려요.",
        listOf(
            InterestCategory.INTEREST_LIVING_INTERIOR,
            InterestCategory.INTEREST_CONTENTS,
            InterestCategory.INTEREST_TRAVEL,
        ),
    )
    val articles = listOf(
        DailyArticleModel(
            "주간 컴퍼니타임스",
            "",
            "\uD83E\uDD94정원 늘어난다 쭉쭉쭉쭉~?",
            1,
            "",
        ),
        DailyArticleModel(
            "주간 컴퍼니타임스",
            "",
            "\uD83E\uDD94정원 늘어난다 쭉쭉쭉쭉~?",
            1,
            "",
        ),
        DailyArticleModel(
            "주간 컴퍼니타임스",
            "",
            "\uD83E\uDD94정원 늘어난다 쭉쭉쭉쭉~?",
            1,
            "",
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background_System)
    ) {
        TopBar(
            title = stringResource(R.string.newsletter_home_title),
            onNavigationIconClick = onBack,
        )
        Box(
            modifier = Modifier
                .pullRefresh(
                    state = pullRefreshState
                ),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                NewsLetterCard(newsLetter)
                NewsLetterNameCard(
                    newsLetter,
                    onSubscribeClick = {

                    },
                    modifier = Modifier
                        .offset(y = (-20).dp)
                        .padding(horizontal = 26.dp)
                )
                NewsLetterIntroduction(
                    newsLetter,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                NewsLetterHistory(
                    articles = articles
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(grayAreaHeightDp)
                    .background(Background_System),
                contentAlignment = Alignment.Center
            ) {
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                )
            }
        }
    }
}

@Composable
fun NewsLetterCard(
    newsLetter: NewsLetterModel,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                newsLetter.interests.forEach { interest ->
                    InterestTag(interest)
                }
            }
            Image(
                painter = painterResource(R.drawable.img_sample),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            )
        }
    }
}

@Composable
fun NewsLetterNameCard(
    newsLetter: NewsLetterModel,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.White.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.04f))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 8.dp)
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    spotColor = Color.Black.copy(alpha = 0.04f)
                )
                .background(Color.White.copy(alpha = 0.4f))
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = newsLetter.name,
                    style = Body1Normal,
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
                        text = newsLetter.repeatTerm,
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral
                    )
                }
            }
            SolidPrimaryButton(
                buttonText = stringResource(R.string.subscribe),
                buttonSize = ButtonSize.MEDIUM,
                onClick = onSubscribeClick
            )
        }
    }
}

@Composable
fun NewsLetterIntroduction(
    newsLetter: NewsLetterModel,
    modifier: Modifier = Modifier
) {
    Text(
        text = newsLetter.introduction,
        style = Body2Reading,
        fontWeight = FontWeight.Normal,
        color = Gray700,
        modifier = modifier
            .padding(bottom = 12.dp)
    )
}


@Composable
fun NewsLetterHistory(
    articles: List<DailyArticleModel>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.newsletter_history_title),
            style = Body2Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Neutral,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        articles.forEach { article ->
            ArticleHistoryItem(article)
        }
    }
}

@Preview(
    name = "NewsLetterDetailScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun NewsLetterDetailScreenPreview() {
    DefaultWhiteTheme {
        NewsLetterDetailScreen(
            onBack = {

            }
        )
    }
}