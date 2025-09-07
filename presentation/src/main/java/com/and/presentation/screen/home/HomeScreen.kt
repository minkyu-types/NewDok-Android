package com.and.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.Article
import com.and.newdok.presentation.R
import com.and.presentation.component.dialog.CalendarDialog
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.toDailyArticleModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.UiState
import com.and.presentation.util.removeRippleEffect
import com.and.presentation.util.toLocalDateWithKRFormat
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onArticleClick: (DailyArticleModel) -> Unit,
    onSearchClick: () -> Unit,
    onAlarmClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.articlesUiState

    var showDialog by remember { mutableStateOf(false) }
    var currSelectedDate: LocalDate by remember { mutableStateOf(LocalDate.now()) }
    val refreshing by remember { derivedStateOf { uiState is UiState.Loading } }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            viewModel.getArticlesByYearMonth(
                currSelectedDate.year,
                currSelectedDate.monthValue,
                currSelectedDate.dayOfMonth
            )
        }
    )

    LaunchedEffect(currSelectedDate) {
        viewModel.getArticlesByYearMonth(
            currSelectedDate.year,
            currSelectedDate.monthValue,
            currSelectedDate.dayOfMonth
        )
    }

    if (showDialog) {
        CalendarDialog(
            onDismiss = {
                showDialog = false
            },
            onDateSelected = { date ->
                currSelectedDate = date
            },
            initialDate = currSelectedDate
        )
    }

    Column(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
            .background(color = Background_System)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        HomeTopBar(
            onSearchClick = onSearchClick,
            onAlarmClick = onAlarmClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        HomeCalendarBar(
            date = currSelectedDate,
            onCalendarClick = {
                showDialog = true
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        HomeArticleList(
            articles = when (uiState) {
                is UiState.Success<List<Article>> -> {
                    (uiState as UiState.Success).data
                }

                else -> emptyList()
            },
            onRefreshClick = {
                // 아티클 리스트 갱신
            },
            onItemClick = {
                onArticleClick(it.toDailyArticleModel())
            },
        )
    }
}

@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit,
    onAlarmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onSearchClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_line_search),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onSearchClick() }
            )
        }
        IconButton(
            onClick = onAlarmClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_line_bell),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onAlarmClick() }
            )
        }
    }
}

@Composable
fun HomeCalendarBar(
    date: LocalDate,
    onCalendarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .removeRippleEffect {
                onCalendarClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(vertical = 12.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date.toLocalDateWithKRFormat(),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null
            )
        }
    }
}

@Composable
fun HomeArticleList(
    onRefreshClick: () -> Unit,
    articles: List<Article>,
    onItemClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.home_daily_article_title, articles.size),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .removeRippleEffect { onRefreshClick() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
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
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(articles) { item ->
                HomeArticleListItem(
                    item,
                    onArticleClick = {
                        onItemClick(item)
                    })
            }
        }
    }
}

@Composable
fun HomeArticleListItem(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val isArticleRead = Article.getIsRead(article.status)

    Row(
        modifier = Modifier
            .clickable {
                onArticleClick(article)
            }
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (isArticleRead) Line_Neutral
                else Color.White
            )
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
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
                .padding(top = 6.dp, end = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.brandName,
                    style = Caption,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = if (isArticleRead) stringResource(R.string.read)
                    else stringResource(R.string.unread),
                    style = Caption,
                    fontWeight = FontWeight.Medium,
                    color = if (isArticleRead) Caption_Strong
                    else Primary_Normal,
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = article.title,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Strong
            )
        }

    }
}

@Preview(
    name = "HomeScreen Preview",
    showBackground = true
)
@Composable
fun HomeScreenPreview() {
    DefaultWhiteTheme {
        HomeScreen(
            onArticleClick = {

            },
            onSearchClick = {

            },
            onAlarmClick = {

            }
        )
    }
}