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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.presentation.R
import com.and.presentation.component.item.NewsLetterBigItem
import com.and.presentation.component.item.NewsLetterSmallItem
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.model.RecommendedNewsLettersModel
import com.and.presentation.ui.Blue50
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.UiState

/**
 * 추천 뉴스레터 페이지 - 회원 프로필이 등록된 경우
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomizedNewsLettersScreen(
    nickname: String,
    onNewsLetterClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CustomizedNewsLettersViewModel = hiltViewModel()
) {
    val uiState by viewModel.customizedNewsLettersUiState

    LaunchedEffect(Unit) {
        viewModel.getCustomizedNewsLetters()
    }

    when (uiState) {
        is UiState.Idle, is UiState.Loading -> {
            // 로딩 중
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            val message = (uiState as UiState.Error).message
            // 에러 화면
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "오류가 발생했습니다.", style = Body1Normal, color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = message ?: "알 수 없는 오류", style = Body2Normal)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getCustomizedNewsLetters() }) {
                        Text("다시 시도")
                    }
                }
            }
        }

        is UiState.Success<*> -> {
            // 성공: intersection, union 리스트 추출
            val data = (uiState as UiState.Success<RecommendedNewsLettersModel>).data
            val intersectionList = data.intersection
            val unionList = data.union

            // pager 상태 (intersection 크기에 맞춤)
            val pagerState = rememberPagerState(pageCount = { intersectionList.size })

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
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
                        NewsLetterBigItem(
                            newsLetter = intersectionList[page],
                            onClick = onNewsLetterClick
                        )
                    }
                }
                CustomizedNewsLettersDotsIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                RecommendOtherNewsLettersArea(
                    recommendedNewsLetters = unionList,
                    onClick = onNewsLetterClick,
                    onRefreshClick = { viewModel.getCustomizedNewsLetters() }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
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
    recommendedNewsLetters: List<RecommendedNewsLetterModel>,
    onClick: () -> Unit,
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
            NewsLetterSmallItem(
                newsLetter = NewsLetterModel(
                    newsLetter.brandName,
                    newsLetter.imageUrl,
                    repeatTerm = newsLetter.publicationCycle,
                    introduction = newsLetter.firstDescription,
                    interests = newsLetter.interests
                ),
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}