package com.and.presentation.screen.mypage

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
import com.and.presentation.R
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary_Normal

@Composable
fun FindAccountScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(FindAccountTab.FIND_ID) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.find_account),
            onNavigationIconClick = onBack,
        )
        FindAccountTabIndicator(
            selectedTab = selectedTab,
            onTabChange = { currentTab ->
                selectedTab = currentTab
            }
        )
    }
}

@Composable
private fun FindAccountTabIndicator(
    selectedTab: FindAccountTab,
    onTabChange: (FindAccountTab) -> Unit,
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
        FindAccountTab.entries.forEach { currentTab ->
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

@Composable
private fun FindIdPhoneScreen(

) {

}

@Composable
private fun FindIdResultScreen(

) {

}

@Composable
private fun FindPasswordPhoneScreen(

) {

}

@Composable
private fun FindPasswordAuthScreen(

) {

}

@Preview(
    name = "FindAccountScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun FindAccountScreenPreview() {
    DefaultWhiteTheme {
        FindAccountScreen(
            onBack = {

            }
        )
    }
}