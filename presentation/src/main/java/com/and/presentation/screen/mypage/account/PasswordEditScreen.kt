package com.and.presentation.screen.mypage.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.textfield.HintErrorSecureTextField
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun PasswordEditScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userPassword = "asdf"

    var prevPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newPasswordConfirm by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.account_manage_password),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
        ) {
            HintErrorSecureTextField(
                icon = R.drawable.ic_line_lock,
                value = prevPassword,
                onValueChange = { prevPassword = it },
                valueTitle = stringResource(id = R.string.password_edit_current_title),
                valueHint = stringResource(id = R.string.password_edit_current_body),
                isError = (userPassword != prevPassword),
                errorMessage = stringResource(id = R.string.password_edit_current_error),
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            )
            HintErrorSecureTextField(
                icon = R.drawable.ic_line_lock,
                value = prevPassword,
                onValueChange = { prevPassword = it },
                valueTitle = stringResource(id = R.string.password),
                valueHint = stringResource(id = R.string.register_password_placeholder),
                isError = (userPassword != prevPassword),
                errorMessage = stringResource(id = R.string.register_password_error_combination),
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            )
            HintErrorSecureTextField(
                icon = R.drawable.ic_line_lock,
                value = prevPassword,
                onValueChange = { prevPassword = it },
                valueTitle = stringResource(id = R.string.password_edit_new_confirm_title),
                valueHint = stringResource(id = R.string.register_password_placeholder),
                isError = (userPassword != prevPassword),
                errorMessage = stringResource(id = R.string.login_password_error),
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            )
        }
        ConditionalNextButton(
            enabled = true,
            onClick = {
                onBack()
            },
            buttonText = stringResource(R.string.edit),
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Preview(
    name = "PasswordEditScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun PasswordEditScreenPreview() {
    DefaultWhiteTheme {
        PasswordEditScreen(
            onBack = {

            }
        )
    }
}