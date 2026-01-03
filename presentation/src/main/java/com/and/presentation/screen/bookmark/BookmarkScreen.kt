@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
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
import com.and.newdok.presentation.R
import com.and.presentation.component.item.SelectableInterestTag
import com.and.presentation.component.topbar.MainTopBar
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticleModel
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticlesModel
import com.and.presentation.model.bookmarkedarticle.MonthlyBookmarkedArticlesModel
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
import com.and.presentation.util.UiState
import com.and.presentation.util.removeRippleEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BookmarkScreen(
    onSearchClick: () -> Unit,
    onArticleClick: (DailyArticleModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val selectedInterests = viewModel.selectedInterests
    val uiState by viewModel.interestedArticlesUiState
    val articleCount by remember(uiState) {
        derivedStateOf {
            (uiState as? UiState.Success<BookmarkedArticlesModel>)?.data?.totalAmount ?: 0
        }
    }
    val monthlyArticles by remember(uiState) {
        derivedStateOf {
            (uiState as? UiState.Success<BookmarkedArticlesModel>)?.data?.bookmarkForMonth
                ?: emptyList()
        }
    }

    BookmarkContent(
        selectedInterests = selectedInterests,
        articleCount = articleCount,
        monthlyArticles = monthlyArticles,
        onSearchClick = onSearchClick,
        onArticleClick = onArticleClick,
        onInterestClick = { viewModel.toggleInterest(it) },
        modifier = modifier
    )
}

@Composable
fun BookmarkContent(
    selectedInterests: Set<InterestCategory>,
    articleCount: Int,
    monthlyArticles: List<MonthlyBookmarkedArticlesModel>,
    onSearchClick: () -> Unit,
    onArticleClick: (DailyArticleModel) -> Unit,
    onInterestClick: (InterestCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()
    var currentSort by remember { mutableStateOf(ArticleSortCategory.SORT_RECENT_ADDED) }
    var showSortBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    fun hideFilter() {
        coroutineScope.launch {
            showSortBottomSheet = false
            sheetState.hide()
        }
    }

    if (showSortBottomSheet) {
        BookmarkSortFilterBottomSheet(
            title = currentSort.value,
            sheetState = sheetState,
            prevSort = currentSort,
            onDismiss = {
                currentSort = it
                hideFilter()
            },
            onHideRequested = {
                hideFilter()
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        MainTopBar(
            title = stringResource(R.string.bookmark_title),
            onSearchClick = onSearchClick,
            onAlarmClick = { }
        )
        BookmarkFilters(
            selectedInterests = selectedInterests,
            onInterestClick = onInterestClick
        )
        BookmarkResultBar(
            currentSort = currentSort,
            articleCount = articleCount,
            onSortClick = {
                showSortBottomSheet = true
            }
        )
        BookmarkArticleList(
            monthlyBookmarkedArticles = monthlyArticles,
            onArticleClick = onArticleClick,
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
private fun BookmarkFilters(
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
    monthlyBookmarkedArticles: List<MonthlyBookmarkedArticlesModel>,
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
        if (monthlyBookmarkedArticles.isEmpty()) {
            ArticleEmptyView()
        } else {
            ArticleExistView(
                articles = monthlyBookmarkedArticles,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ArticleExistView(
    articles: List<MonthlyBookmarkedArticlesModel>,
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
        items(articles) { monthModel ->
            Text(
                text = monthModel.month,
                style = Headline,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )
            monthModel.articles.forEach { article ->
                BookmarkArticleItem(
                    article = article,
                    onArticleClick = onArticleClick
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
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
    name = "BookmarkScreen Preview - Empty",
    showBackground = true
)
@Composable
fun BookmarkScreenEmptyPreview() {
    DefaultWhiteTheme {
        BookmarkContent(
            selectedInterests = setOf(
                InterestCategory.INTEREST_ECONOMY_AFFAIRS,
                InterestCategory.INTEREST_BUSINESS
            ),
            articleCount = 0,
            monthlyArticles = emptyList(),
            onSearchClick = { },
            onArticleClick = { },
            onInterestClick = { }
        )
    }
}

@Preview(
    name = "BookmarkScreen Preview - With Articles",
    showBackground = true
)
@Composable
fun BookmarkScreenWithArticlesPreview() {
    DefaultWhiteTheme {
        BookmarkContent(
            selectedInterests = setOf(InterestCategory.INTEREST_ECONOMY_AFFAIRS),
            articleCount = 5,
            monthlyArticles = listOf(
                MonthlyBookmarkedArticlesModel(
                    id = 1,
                    month = "2024년 1월",
                    articles = listOf(
                        BookmarkedArticleModel(
                            brandName = "뉴스 출판사",
                            brandId = 1,
                            articleTitle = "샘플 기사 제목입니다",
                            articleId = 1,
                            sampleText = "샘플 기사 설명입니다",
                            date = "2024.01.15",
                            thumbnailImageUrl = null
                        )
                    )
                )
            ),
            onSearchClick = { },
            onArticleClick = { },
            onInterestClick = { }
        )
    }
}