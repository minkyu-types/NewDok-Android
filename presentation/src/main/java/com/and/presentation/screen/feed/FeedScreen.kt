package com.and.presentation.screen.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.and.newdok.presentation.R
import com.and.presentation.component.topbar.MainTopBar
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary_Normal

@Composable
fun FeedScreen(
    onNewsLetterClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onAlarmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(FeedTab.RECOMMEND) }
    val isProfileRegistered by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        MainTopBar(
            title = stringResource(R.string.feed_title),
            onSearchClick = onSearchClick,
            onAlarmClick = onAlarmClick
        )
        FeedTabIndicator(
            selectedTab = selectedTab,
            onTabChange = { tab ->
                selectedTab = tab
            }
        )
        when (selectedTab) {
            FeedTab.RECOMMEND -> {
                if (isProfileRegistered) {
                    CustomizedNewsLettersScreen(
                        nickname = "기무민규",
                        onNewsLetterClick = { id ->
                            onNewsLetterClick(id)
                        }
                    )
                } else {
                    RecommendNewsLetterForNoUserProfile()
                }
            }

            FeedTab.ALL -> {
                AllNewsLettersScreen(
                    onNewsLetterClick = { id ->
                        onNewsLetterClick(id)
                    },
                    onResetClick = {

                    }
                )
            }
        }
    }
}

@Composable
fun FeedTabIndicator(
    selectedTab: FeedTab,
    onTabChange: (FeedTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = selectedTab.index
    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = Caption_Strong
            )
        },
        containerColor = Color.White
    ) {
        FeedTab.entries.forEachIndexed { index, currentTab ->
            val isSelected = selectedTabIndex == index
            Tab(
                selected = isSelected,
                onClick = {
                    onTabChange(currentTab)
                },
                text = {
                    Text(
                        text = currentTab.title,
                        style = Body2Normal,
                        fontWeight = if (isSelected) FontWeight.Bold
                        else FontWeight.Medium,
                        color = if (isSelected) Caption_Strong
                        else Caption_Alternative
                    )
                },
                selectedContentColor = Primary_Normal
            )
        }
    }
}

/**
 * 추천 뉴스레터 - 회원 프로필이 등록되지 않은 경우
 */
@Composable
fun RecommendNewsLetterForNoUserProfile(
    modifier: Modifier = Modifier
) {

}

@Preview(
    name = "FeedScreen Preview",
    showBackground = true
)
@Composable
fun FeedScreenPreview() {
    DefaultWhiteTheme {
        FeedScreen(
            onNewsLetterClick = {

            },
            onSearchClick = {

            },
            onAlarmClick = {

            }
        )
    }
}