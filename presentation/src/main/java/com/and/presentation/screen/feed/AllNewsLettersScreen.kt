package com.and.presentation.screen.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.type.NewsLetterFilterCategory
import com.and.presentation.R
import com.and.presentation.component.item.FilterChip
import com.and.presentation.component.item.NewsLetterSmallItem
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.UiState

/**
 * 모든 뉴스레터
 */
@Composable
fun AllNewsLettersScreen(
    onNewsLetterClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AllNewsLettersViewModel = hiltViewModel()
) {
    val uiState by viewModel.allNewsLettersUiState
    var selectedFilters by remember { mutableStateOf(SelectedFilters()) }

    LaunchedEffect(selectedFilters) {
        viewModel.getAllNewsLetters(
            selectedFilters.sort,
            selectedFilters.industries,
            selectedFilters.date
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_System)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                AllNewsLetterFilters(
                    selectedFilters = selectedFilters,
                    onResetClick = {
                        selectedFilters = SelectedFilters()
                    }
                )
            }
            when (uiState) {
                is UiState.Loading -> {
                    item {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is UiState.Success -> {
                    val newsLetters = (uiState as UiState.Success<List<NewsLetterModel>>).data
                    items(newsLetters) { newsLetter ->
                        NewsLetterSmallItem(
                            newsLetter = newsLetter,
                            onClick = onNewsLetterClick,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                        )
                    }
                }

                else -> {

                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllNewsLetterFilters(
    selectedFilters:  SelectedFilters,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(Background_System)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState())
                ) {
                    NewsLetterFilterCategory.entries
                        .zip(
                            listOf(
                                painterResource(R.drawable.ic_line_arrow_transfer),
                                painterResource(R.drawable.ic_line_down),
                                painterResource(R.drawable.ic_line_down),
                            )
                        )
                        .forEach { (filter, icon) ->
                            FilterChip(
                                text = filter.default,
                                icon = icon,
                                leastOneItemSelected = SelectedFilters.getIsChanged(
                                    selectedFilters,
                                    filter
                                ),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                }
            }
            Icon(
                painter = painterResource(R.drawable.ic_line_reload),
                tint = Primary_Normal,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onResetClick()
                    }
                    .size(24.dp)
            )
        }
    }
}