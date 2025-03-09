package com.and.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.and.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedPrimaryButton
import com.and.presentation.component.item.ArticleItem
import com.and.presentation.component.item.NewsLetterSimpleItem
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body1Reading
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        SearchTopBar(
            query = query,
            onBack = onBack,
            onValueChange = {
                query = it
            }
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        if (query.isEmpty()) {
            SearchPopularQueries(
                updatedDate = "11/21",
                popularQueries = listOf(
                    "뉴닉",
                    "데일리바이트",
                    "트렌드",
                    "콘텐츠",
                    "재테크",
                    "IT",
                ),
                modifier = Modifier.weight(1f)
            )
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
//                    QueryNewsLetterResult(
//                        newsLetters =
//                    )
                }
                item {
//                    QueryArticleResult(
//                        articles =
//                    )
                }
            }
        }
    }
}

@Composable
private fun SearchTopBar(
    query: String,
    onBack: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.back),
            tint = Caption_Assistive,
            modifier = Modifier
                .removeRippleEffect { onBack() }
                .size(24.dp)
        )
        TextField(
            value = query,
            onValueChange = onValueChange,
            textStyle = Body2Normal,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder),
                    style = Body2Normal,
                    fontWeight = FontWeight.Normal,
                    color = Caption_Assistive
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Caption_Strong,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedTextColor = Caption_Strong
            ),
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Line_Alternative)
                            .size(20.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_line_close),
                            tint = Color.White,
                            contentDescription = null,
                        )
                    }
                }
            },
            modifier = Modifier.weight(1f),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_line_search),
            contentDescription = stringResource(id = R.string.search),
            modifier = Modifier
                .removeRippleEffect { onBack() }
                .size(24.dp)
        )
    }
}

/**
 * @param updatedDate 업데이트된 날짜
 *
 *
 * ```
 * 형식: "11/21"
 * ```
 */
@Composable
private fun SearchPopularQueries(
    updatedDate: String,
    popularQueries: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.search_popular_queries_title),
                style = Body1Reading,
                fontWeight = FontWeight.Medium,
                color = Caption_Heavy
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.search_popular_queries_update_date, updatedDate),
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Primary_Normal
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        popularQueries.forEachIndexed { index, query ->
            PopularQueryItem(index+1, query)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun PopularQueryItem(
    rank: Int,
    query: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$rank",
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Primary_Normal
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = query,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral
        )
    }
}

@Composable
private fun QueryNewsLetterResult(
    newsLetters: LazyPagingItems<NewsLetterModel>,
    onNewsLetterClick: (NewsLetterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val newsLetterCount = newsLetters.itemCount
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.search_result_newsletter_title),
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy,
        )
        if (newsLetterCount == 0) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.search_result_empty),
                    style = Body1Reading,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy
                )
                Text(
                    text = stringResource(R.string.search_newsletter_empty_body),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedPrimaryButton(
                    buttonText = stringResource(R.string.search_newsletter_request),
                    buttonSize = ButtonSize.LARGE,
                    onClick = {
                        // 뉴스레터 요청 화면으로 이동
                    }
                )
            }
        } else {
            LazyColumn {
                items(newsLetterCount) { index ->
                    newsLetters[index]?.let { newsLetter ->
                        NewsLetterSimpleItem(
                            newsLetter = newsLetter,
                            onClick = {
                                onNewsLetterClick(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QueryArticleResult(
    articles: LazyPagingItems<DailyArticleModel>,
    onArticleClick: (DailyArticleModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val articleCount = articles.itemCount
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.search_result_article_title, articleCount),
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy,
        )
        if (articleCount == 0) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.search_result_empty),
                    style = Body1Reading,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy
                )
                Text(
                    text = stringResource(R.string.search_article_empty_body),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedPrimaryButton(
                    buttonText = stringResource(R.string.search_newsletter_request),
                    buttonSize = ButtonSize.LARGE,
                    onClick = {
                        // 뉴스레터 요청 화면으로 이동
                    }
                )
            }
        } else {
            LazyColumn {
                items(articleCount) { index ->
                    articles[index]?.let { article ->
                        ArticleItem(
                            article = article,
                            onArticleClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "SearchScreen Preview",
    showBackground = true
)
@Composable
private fun SearchScreenPreview() {
    DefaultWhiteTheme {
        SearchScreen(
            onBack = {

            }
        )
    }
}