@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.and.presentation.screen.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.NewsLetterFilterCategory
import com.and.domain.model.type.PublicationDay
import com.and.domain.model.type.SortCategory
import com.and.newdok.presentation.R
import com.and.presentation.component.dialog.BottomSheetDialog
import com.and.presentation.component.item.FilterChip
import com.and.presentation.component.item.NewsLetterSmallSubscriptionItem
import com.and.presentation.model.NewsLetterSubscriptionModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.UiState
import kotlinx.coroutines.launch

/**
 * 모든 뉴스레터
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllNewsLettersScreen(
    onNewsLetterClick: (Int) -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AllNewsLettersViewModel = hiltViewModel()
) {
    val uiState by viewModel.newsLettersUiState.collectAsState()

    var selectedFilter by remember { mutableStateOf<NewsLetterFilterCategory?>(null) }

    var currentSort by remember { mutableStateOf(SortCategory.POPULARITY) }
    var currentIndustries by remember { mutableStateOf(listOf(IndustryCategory.DEFAULT)) }
    var currentPublicationDays by remember { mutableStateOf(listOf(PublicationDay.MONDAY)) }

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentSort) { viewModel.setSort(currentSort) }
    LaunchedEffect(currentIndustries) { viewModel.setIndustries(currentIndustries) }
    LaunchedEffect(currentPublicationDays) { viewModel.setDays(currentPublicationDays) }

    fun showFilter(filter: NewsLetterFilterCategory) {
        coroutineScope.launch {
            selectedFilter = filter
            sheetState.show()
        }
    }

    fun hideFilter() {
        coroutineScope.launch {
            sheetState.hide()
            selectedFilter = null
        }
    }

    if (selectedFilter != null) {
        when (selectedFilter) {
            NewsLetterFilterCategory.SORT -> {
                SortFilterBottomSheet(
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

            NewsLetterFilterCategory.INDUSTRY, NewsLetterFilterCategory.WHEN -> {
                IndustryAndDayBottomSheet(
                    title = stringResource(R.string.filter),
                    sheetState = sheetState,
                    prevIndustryCategory = currentIndustries,
                    prevDayId = currentPublicationDays,
                    onDismiss = { industryCategory, publicationDay ->
                        currentIndustries = industryCategory
                        currentPublicationDays = publicationDay
                        hideFilter()
                    },
                    onHideRequested = {
                        hideFilter()
                    }
                )
            }

            else -> {

            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background_System)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                AllNewsLetterFilters(
                    selectedFilters = SelectedFilters(
                        sort = currentSort,
                        industries = currentIndustries,
                        days = currentPublicationDays
                    ),
                    onResetClick = {
                        // 필터 초기화
                    },
                    onFilterClick = { filter ->
                        showFilter(filter)
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
                    val newsLetters =
                        (uiState as UiState.Success<List<NewsLetterSubscriptionModel>>).data
                    items(newsLetters) { newsLetter ->
                        NewsLetterSmallSubscriptionItem(
                            newsLetter = newsLetter,
                            onClick = {
                                onNewsLetterClick(newsLetter.brandId)
                            },
                            onSubscribeClick = {
                                it
                            },
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
    selectedFilters: SelectedFilters,
    onResetClick: () -> Unit,
    onFilterClick: (NewsLetterFilterCategory) -> Unit,
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
                                text = when (filter) {
                                    NewsLetterFilterCategory.SORT -> selectedFilters.sort.value
                                    NewsLetterFilterCategory.INDUSTRY -> {
                                        val size = selectedFilters.industries.size
                                        if (size > 3) {
                                            "산업 $size"
                                        } else {
                                            selectedFilters.industries
                                                .map { it.value }
                                                .take(2)
                                                .joinToString("·")
                                        }
                                    }
                                    NewsLetterFilterCategory.WHEN -> {
                                        val size = selectedFilters.days.size
                                        if (size > 1) {
                                            "발행요일 $size"
                                        } else {
                                            selectedFilters.days.first().value
                                        }
                                    }
                                },
                                icon = icon,
                                leastOneItemSelected = false,
                                onClick = {
                                    onFilterClick(filter)
                                }
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

@Composable
private inline fun FilterBottomSheet(
    sheetState: SheetState,
    crossinline onDismiss: () -> Unit,
    crossinline onHideRequest: () -> Unit,
    modifier: Modifier
) {
    BottomSheetDialog(
        title = "정렬",
        sheetState = sheetState,
        onDismiss = {
            onDismiss()
        },
        onHideRequested = {
            onHideRequest()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "인기순",
                    modifier = Modifier
                        .height(56.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.height(1.dp),
                    color = Line_Neutral
                )
                Text(
                    text = "최신등록순"
                )
            }
        }
    )
}