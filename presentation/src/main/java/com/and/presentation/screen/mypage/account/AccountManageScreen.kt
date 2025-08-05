package com.and.presentation.screen.mypage.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.newdok.presentation.R
import com.and.presentation.component.dialog.TwoButtonDialog
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.screen.mypage.MyPageItem
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1

@Composable
fun AccountManageScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    onTryWithdrawal: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountManageViewModel = hiltViewModel()
) {
    val logoutResult by viewModel.logoutResult
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(logoutResult)
    {
        if (logoutResult == true) {
            onLogout()
        }
        showLogoutDialog = false
    }

    if (showLogoutDialog) {
        TwoButtonDialog(
            title = stringResource(R.string.account_manage_logout_question),
            body = null,
            leftButtonText = stringResource(R.string.cancel),
            rightButtonText = stringResource(R.string.account_manage_logout),
            onLeftButtonClick = {
                showLogoutDialog = false
            },
            onRightButtonClick = {
                viewModel.clearUserData()
            },
            onDismiss = {
                showLogoutDialog = false
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.my_page_service_item_1),
            onNavigationIconClick = onBack,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            MyPageItem(
                text = stringResource(R.string.account_manage_phone),
                onClick = {

                },
            )
            MyPageItem(
                text = stringResource(R.string.account_manage_password),
                onClick = {

                }
            )
            MyPageItem(
                text = stringResource(R.string.account_manage_logout),
                onClick = {
                    showLogoutDialog = true
                }
            )
            Text(
                text = stringResource(R.string.withdrawal),
                style = Label1.copy(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier
                    .clickable {
                        onTryWithdrawal()
                    }
            )
        }
    }
}

@Preview(
    name = "AccountManagerScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun AccountManagerScreenPreview() {
    DefaultWhiteTheme {
        AccountManageScreen(
            onBack = {

            },
            onLogout = {
                
            },
            onTryWithdrawal = {

            }
        )
    }
}