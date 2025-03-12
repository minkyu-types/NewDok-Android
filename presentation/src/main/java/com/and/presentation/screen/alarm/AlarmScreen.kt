package com.and.presentation.screen.alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.item.NewsLetterSimpleItem
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.screen.feed.FeedTab
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary_Normal
import java.time.LocalDate

@Composable
fun AlarmScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(AlarmTab.ARTICLE) }
    var items by remember { mutableStateOf(emptyList<DailyArticleModel>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.alarm),
            onNavigationIconClick = onBack,
            onActionButtonClick = {

            },
            actionIcon = painterResource(R.drawable.ic_line_settings)
        )
        AlarmTabIndicator(
            selectedTab = selectedTab,
            onTabChange = {
                selectedTab = it
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AlarmList(
            alarms = listOf(
                AlarmModel(
                    title = "정부25 먹통",
                    date = LocalDate.now()
                ),
                AlarmModel(
                    title = "에버다케더브라",
                    date = LocalDate.now().minusDays(6)
                ),
                AlarmModel(
                    title = "해리포터",
                    date = LocalDate.now().minusDays(6)
                ),
                AlarmModel(
                    title = "인테르",
                    date = LocalDate.now().minusDays(6)
                ),
            )
        )
    }
}

@Composable
fun AlarmTabIndicator(
    selectedTab: AlarmTab,
    onTabChange: (AlarmTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = selectedTab.ordinal
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
        AlarmTab.entries.forEach { currentTab ->
            val isSelected = selectedTabIndex == currentTab.ordinal
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

data class AlarmModel (
    val title: String,
    val date: LocalDate
)

@Composable
fun AlarmList(
    alarms: List<AlarmModel>,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val (todayAlarms, otherAlarms) = alarms.partition { alarm ->
        alarm.date.isEqual(today)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.today),
                style = Body2Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Neutral,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        items(todayAlarms) { alarm ->
            NewsLetterSimpleItem(
                newsLetter = NewsLetterModel(
                    "",
                    "",
                    "",
                    "",
                    emptyList(),
                ),
                onClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.since_last_week),
                style = Body2Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Neutral,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        items(otherAlarms) { alarm ->
            NewsLetterSimpleItem(
                newsLetter = NewsLetterModel(
                    "",
                    "",
                    "",
                    "",
                    emptyList(),
                ),
                onClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(
    name = "AlarmScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun AlarmScreenPreview() {
    DefaultWhiteTheme {
        AlarmScreen(
            onBack = {

            }
        )
    }
}