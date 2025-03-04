package com.and.presentation.screen.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.item.NewsLetterBigItem
import com.and.presentation.component.item.NewsLetterSmallItem
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Blue50
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Primary_Normal

/**
 * 추천 뉴스레터 페이지 - 회원 프로필이 등록된 경우
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomizedNewsLettersScreen(
    nickname: String,
    newsLetters: List<NewsLetterModel>,
    recommendedNewsLetters: List<NewsLetterModel>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState() { newsLetters.size }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.feed_recommend_title, nickname),
            style = Heading2,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 24.dp, end = 40.dp)
            ) { page ->
                NewsLetterBigItem(newsLetters[page])
            }
        }
        CustomizedNewsLettersDotsIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        RecommendOtherNewsLettersArea(
            recommendedNewsLetters = recommendedNewsLetters,
            onRefreshClick = {

            }
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun CustomizedNewsLettersDotsIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(bottom = 12.dp, top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        repeat(pagerState.pageCount) { index ->
            val isSelected = (pagerState.currentPage == index)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .background(
                        color = if (isSelected) Primary_Normal
                        else Blue50,
                        shape = CircleShape,
                    )
            )
        }
    }
}

@Composable
fun RecommendOtherNewsLettersArea(
    recommendedNewsLetters: List<NewsLetterModel>,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.feed_recommend_title_2),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = onRefreshClick,
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_line_reload),
                    tint = Primary_Normal,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.refresh),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Primary_Normal
                )
            }
        }
        recommendedNewsLetters.forEach { newsLetter ->
            NewsLetterSmallItem(newsLetter)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}