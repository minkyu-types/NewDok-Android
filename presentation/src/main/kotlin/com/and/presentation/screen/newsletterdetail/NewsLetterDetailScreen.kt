@file:OptIn(ExperimentalMaterial3Api::class)

package com.and.presentation.screen.newsletterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.component.item.InterestTag
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.newdok.presentation.R
import com.and.presentation.component.image.CommonImage
import com.and.presentation.component.item.ArticleHistoryItem
import com.and.presentation.model.NewsLetterDetailModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Body2Reading
import com.and.presentation.ui.Gray700
import com.and.presentation.util.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsLetterDetailScreen(
    id: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsLetterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.newsLetterDetailUiState
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
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val grayAreaHeightDp = (70.dp * pullRefreshState.progress).coerceAtMost(70.dp)

    LaunchedEffect(id) {
        viewModel.getNewsLetterDetail(id)
    }

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
            when (uiState) {
                is UiState.Success<*> -> {
                    val data = (uiState as UiState.Success<NewsLetterDetailModel>).data

                    if (sheetState.isVisible) {
                        SubscriptionBottomSheet(
                            newsLetterDetailModel = data,
                            sheetState = sheetState,
                            onHideRequested = {
                                coroutineScope.launch {
                                    sheetState.hide()
                                }
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        NewsLetterCard(data)
                        NewsLetterNameCard(
                            data,
                            onSubscribeClick = { id, wasSubscribed ->
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            },
                            modifier = Modifier
                                .offset(y = (-20).dp)
                                .padding(horizontal = 26.dp)
                        )
                        NewsLetterIntroduction(
                            data,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        NewsLetterHistory(
                            articles = data.brandArticleList
                        )
                    }
                }

                UiState.Idle, UiState.Loading -> {
                    Box(
                        modifier = modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {

                }
            }
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

@Composable
fun NewsLetterCard(
    newsLetter: NewsLetterDetailModel,
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
            CommonImage(
                imageUrl = newsLetter.imageUrl,
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
    newsLetter: NewsLetterDetailModel,
    onSubscribeClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .blur(16.dp)
            .background(
                color = Color.White.copy(alpha = 0.7f),
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = newsLetter.brandName,
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
                        text = newsLetter.publicationCycle,
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral
                    )
                }
            }
            SolidPrimaryButton(
                buttonText = stringResource(R.string.subscribe),
                buttonSize = ButtonSize.MEDIUM,
                onClick = {
                    onSubscribeClick(
                        newsLetter.brandId,
                        NewsLetterDetailModel.getIsSubscribed(newsLetter)
                    )
                }
            )
        }
    }
}

@Composable
fun NewsLetterIntroduction(
    newsLetter: NewsLetterDetailModel,
    modifier: Modifier = Modifier
) {
    Text(
        text = newsLetter.detailDescription,
        style = Body2Reading,
        fontWeight = FontWeight.Normal,
        color = Gray700,
        modifier = modifier
            .padding(bottom = 12.dp)
    )
}


@Composable
fun NewsLetterHistory(
    articles: List<NewsLetterDetailModel.BrandArticleModel>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.newsletter_history_title),
            style = Body2Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Neutral,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        if (articles.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.newsletter_history_empty_1),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.3).sp
                )
                Text(
                    text = stringResource(R.string.newsletter_history_empty_2)
                )
            }
        } else {
            articles.forEach { article ->
                ArticleHistoryItem(article)
            }
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
            id = 0,
            onBack = {

            }
        )
    }
}