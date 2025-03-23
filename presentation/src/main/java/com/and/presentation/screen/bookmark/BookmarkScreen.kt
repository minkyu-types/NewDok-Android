@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)

package com.and.presentation.screen.bookmark

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.type.ArticleSortCategory
import com.and.domain.model.type.InterestCategory
import com.and.presentation.R
import com.and.presentation.component.item.ArticleItem
import com.and.presentation.component.item.SelectableInterestTag
import com.and.presentation.component.topbar.MainTopBar
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Headline
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()
    val selectedInterests = viewModel.selectedInterests
    var currentSort by remember { mutableStateOf(ArticleSortCategory.SORT_RECENT_ADDED) }
    val filteredArticles = listOf(
        DailyArticleModel(
            "주간 컴퍼니타임스",
            "",
            "신입사원 시절 '최악의 실수'는?",
            1,
            "",
        ),
        DailyArticleModel(
            "고구마팜",
            "",
            "(광고) SNS에서 주목받는 브랜드의 비법 노트",
            1,
            "",
        ),
        DailyArticleModel(
            "머니레터",
            "",
            "어피티 퇴사, 그 이후..",
            1,
            "",
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        MainTopBar(
            title = stringResource(R.string.bookmark_title),
            onSearchClick = {

            },
            onAlarmClick = {

            }
        )
        BookmarkFilters(
            selectedInterests = selectedInterests,
            onInterestClick = { interest ->
                viewModel.toggleInterest(interest)
            }
        )
        BookmarkResultBar(
            currentSort = currentSort,
            articleCount = filteredArticles.size,
            onSortClick = {
                // 바텀 시트 오픈
            }
        )
        BookmarkArticleList(
            articles = emptyList(),
            onArticleClick = { article ->
                // 아티클 상세 화면으로 이동
            },
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                coroutineScope.launch {
                    delay(1000L)
                    isRefreshing = false
                }
            },
            refreshState = refreshState
        )
    }
}

@Composable
fun BookmarkFilters(
    selectedInterests: Set<InterestCategory>,
    onInterestClick: (InterestCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val interests = InterestCategory.entries

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
            items(interests) { interest ->
                SelectableInterestTag(
                    interest = interest,
                    isSelected = (interest in selectedInterests),
                    onClick = { onInterestClick(it) },
                )
            }
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun BookmarkResultBar(
    currentSort: ArticleSortCategory,
    articleCount: Int,
    onSortClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.bookmark_count_title, articleCount),
            style = Heading2,
            fontWeight = FontWeight.Bold,
            color = Primary_Normal
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .removeRippleEffect { onSortClick() }
                .padding(horizontal = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentSort.value,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Strong
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_line_arrow_transfer),
                    tint = Caption_Strong,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun BookmarkArticleList(
    articles: List<DailyArticleModel>,
    onArticleClick: (DailyArticleModel) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    refreshState: PullToRefreshState,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = refreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = Color.White,
                color = Primary_Normal,
                state = refreshState
            )
        },
    ) {
        // TODO 아티클 년-월 기준으로 년-월 헤더 추가해서 리스트로
        if (articles.isEmpty()) {
            ArticleEmptyView()
        } else {
            ArticleExistView(
                articles = articles,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ArticleExistView(
    articles: List<DailyArticleModel>,
    onArticleClick: (DailyArticleModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Background_System)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        item {
            Text(
                text = "2023년 11월",
                style = Headline,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(articles) { article ->
            ArticleItem(
                article = article,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ArticleEmptyView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background_System)
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_empty_article),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.bookmark_empty_title),
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.bookmark_empty_body),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral
        )
    }
}

@Preview(
    name = "BookmarkScreen Preview",
    showBackground = true
)
@Composable
fun BookmarkScreenPreview() {
    DefaultWhiteTheme {
        BookmarkScreen(
        )
    }
}