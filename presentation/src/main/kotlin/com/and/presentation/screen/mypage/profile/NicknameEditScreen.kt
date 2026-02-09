package com.and.presentation.screen.mypage.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.UserModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.UiState
import com.and.presentation.util.nicknameValidation

@Composable
fun NicknameEditScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileEditViewModel
) {
    var nickname by remember { mutableStateOf("") }
    val isNicknameValid = nickname.nicknameValidation()
    val userInfo by viewModel.userInfoUiState

    val userNickName = when(userInfo) {
        is UiState.Success<UserModel> -> (userInfo as UiState.Success<UserModel>).data.nickname
        else -> ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.profile_edit_nickname_title),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.nickname),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 4.dp),
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.height(8.dp))
            HintErrorTextField(
                maxLength = 12,
                value = nickname,
                valueHint = userNickName,
                isError = !isNicknameValid,
                onValueChange = { nickname = it }
            )
            if (nickname.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                if (!isNicknameValid) {
                    Text(
                        text = stringResource(R.string.register_nickname_placeholder),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = stringResource(R.string.register_nickname_valid),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Primary_Normal
                    )
                }
            }
        }
        ConditionalNextButton(
            enabled = isNicknameValid,
            onClick = {
                viewModel.updateNickName(nickname)
                onBack()
            },
            buttonText = stringResource(R.string.edit),
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Preview(
    name = "NicknameEditScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun NicknameEditScreenPreview() {
    DefaultWhiteTheme {
        NicknameEditScreen(
            onBack = {

            },
            viewModel = hiltViewModel()
        )
    }
}