package com.and.presentation.screen.mypage.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.newdok.presentation.R
import com.and.presentation.component.CustomSwitch
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Primary_Normal

@Composable
fun NotificationSettingScreen(
    onBack: () -> Unit,
    viewModel: NotificationSettingViewModel,
) {
    val settingState by viewModel.notificationSettingsFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.my_page_service_item_2),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.article),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.height(24.dp))
            NotificationSettingItem(
                title = stringResource(R.string.notification_setting_title_1),
                body = stringResource(R.string.notification_setting_body_1),
                initialState = settingState.newArticleEnabled,
                onSwitchStateChange = {
                    viewModel.onToggleNewArticle()
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.new_things),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.height(24.dp))
            NotificationSettingItem(
                title = stringResource(R.string.notification_setting_title_2),
                body = stringResource(R.string.notification_setting_body_2),
                initialState = settingState.newUpdateEnabled,
                onSwitchStateChange = {
                    viewModel.onToggleNewUpdate()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            NotificationSettingItem(
                title = stringResource(R.string.notification_setting_title_3),
                body = stringResource(R.string.notification_setting_body_3),
                initialState = settingState.recommendedNewsletterEnabled,
                onSwitchStateChange = {
                    viewModel.onToggleRecommendedNewsLetters()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            NotificationBottomItem()
        }
    }
}

@Composable
private fun NotificationSettingItem(
    title: String,
    body: String,
    initialState: Boolean,
    onSwitchStateChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = title,
                style = Body1Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Heavy,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = body,
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        CustomSwitch(
            checked = initialState,
            onCheckedChange = onSwitchStateChange,
        )
    }
}

@Composable
private fun NotificationBottomItem(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Background_System)
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_line_question_mark),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
                tint = Caption_Assistive
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.notification_setting_bottom_1),
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Text(
            text = stringResource(R.string.notification_setting_bottom_2),
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = Primary_Normal,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(
    name = "NotificationSettingScreen Preview",
    showBackground = true
)
@Composable
fun NotificationSettingScreenPreview() {
    DefaultWhiteTheme {
        NotificationSettingScreen(
            onBack = {

            },
            viewModel = hiltViewModel()
        )
    }
}