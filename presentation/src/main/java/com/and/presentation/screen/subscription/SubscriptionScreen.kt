package com.and.presentation.screen.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.presentation.R
import com.and.presentation.component.item.NewsLetterSubscriptionItem
import com.and.presentation.component.topbar.MainTopBar
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.util.UiState
import com.and.presentation.util.removeRippleEffect

@Composable
fun SubscriptionScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SubscriptionViewModel = hiltViewModel()
) {
    val uiState by viewModel.subscribedUiState
    val newsLetters by remember {
        mutableStateOf(
            when (uiState) {
                is UiState.Success<List<BriefNewsLetterModel>> -> {
                    (uiState as UiState.Success).data
                }
                else -> emptyList()
            }
        )
    }
    var currentTab by remember { mutableStateOf(SubscriptionTab.ING) }

    LaunchedEffect(currentTab) {
        when (currentTab) {
            SubscriptionTab.ING -> viewModel.getSubscribedNewsLetters()
            SubscriptionTab.PAUSED -> viewModel.getUnsubscribedNewsLetters()
        }
    }

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
        SubscriptionSegmentedTab(
            selectedTab = currentTab,
            onTabChange = {
                currentTab = it
            }
        )
        if (newsLetters.isEmpty()) {
            SubscribedNewsLettersEmptyView()
        } else {
            SubscribedNewsLettersExistView(
                newsLetters = newsLetters,
                onSubscribeClick = { newsLetter ->
                    // 구독중이라면 구독 중지
                }
            )
        }
    }
}

@Composable
fun SubscriptionSegmentedTab(
    selectedTab: SubscriptionTab,
    onTabChange: (SubscriptionTab) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(6.dp)
            )
            .background(
                color = Line_Neutral
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        )
        {
            SubscriptionTab.entries.forEachIndexed { index, currentTab ->
                val isSelected = (index == selectedIndex)
                Box(
                    modifier = Modifier
                        .removeRippleEffect { selectedIndex = index }
                        .weight(1f)
                        .shadow(
                            elevation = if (isSelected) 2.dp else 0.dp,
                            shape = RoundedCornerShape(6.dp),
                            clip = true
                        )
                        .background(
                            color = if (isSelected) Color.White else Line_Neutral,
                            shape = if (isSelected) RoundedCornerShape(6.dp) else RoundedCornerShape(
                                0.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = currentTab.title,
                        style = Body2Normal,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Caption_Heavy else Caption_Alternative,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SubscribedNewsLettersEmptyView(
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
            painter = painterResource(R.drawable.img_bookmark_empty),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.subscribe_empty_title),
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.subscribe_count_body),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral
        )
    }
}

@Composable
fun SubscribedNewsLettersExistView(
    newsLetters: List<BriefNewsLetterModel>,
    onSubscribeClick: (BriefNewsLetterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Background_System)
            .padding(horizontal = 24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.subscribe_count_title, newsLetters.size),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.subscribe_count_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(newsLetters) { newsLetter ->
            NewsLetterSubscriptionItem(
                newsLetter = newsLetter,
                onSubscribeClick = onSubscribeClick
            )
        }
    }
}

@Preview(
    name = "SubscriptionScreen Preview",
    showBackground = true
)
@Composable
fun SubscriptionScreenPreview() {
    DefaultWhiteTheme {
        SubscriptionScreen(
            onSearchClick = {

            }
        )
    }
}