package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.and.newdok.presentation.R
import com.and.presentation.component.textfield.HintErrorSecureTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.util.PASSWORD_MIN_LENGTH
import com.and.presentation.util.passwordValidation

/**
 * TODO
 * (1) 비밀번호가 입력되었고 조건을 충족할 때, 비밀번호 확인의 길이가 비밀번호랑 같아지면
 *     두 값을 비교해서 일치하지 않으면 비밀번호 확인 TextField에 errorText 보여줄 것
 * (2) 영어/숫자만 입력이 가능하도록 할 것
 */
@Composable
fun RegisterStep3Screen(
    onNext: () -> Unit,
    onBack: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    var userPassword by remember { mutableStateOf("") }
    var userPasswordConfirm by remember { mutableStateOf("") }
    val isUserPasswordCombinationValid = userPassword.passwordValidation()
    val isUserPasswordValid = userPassword.length >= PASSWORD_MIN_LENGTH
            && isUserPasswordCombinationValid
    val isUserPasswordConfirmValid = userPasswordConfirm == userPassword
    val isPasswordValid = isUserPasswordValid && isUserPasswordConfirmValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_password_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))

            HintErrorSecureTextField(
                icon = R.drawable.ic_line_lock,
                value = userPassword,
                onValueChange = { userPassword = it },
                valueTitle = stringResource(id = R.string.password),
                valueHint = stringResource(id = R.string.login_password_hint),
                isError = if (userPassword.isNotBlank()) {
                    !isUserPasswordCombinationValid || !isUserPasswordValid
                } else {
                    false
                },
                errorMessage = if (!isUserPasswordCombinationValid) {
                    stringResource(id = R.string.register_password_error_combination)
                } else {
                    stringResource(id = R.string.register_password_error_length)
                },
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            HintErrorSecureTextField(
                icon = R.drawable.ic_line_lock,
                value = userPasswordConfirm,
                onValueChange = { userPasswordConfirm = it },
                valueTitle = stringResource(id = R.string.password_confirm),
                valueHint = stringResource(id = R.string.login_password_hint),
                isError = if (userPasswordConfirm.isNotBlank()) {
                    !isUserPasswordConfirmValid || !isUserPasswordValid
                } else {
                    false
                },
                errorMessage = stringResource(id = R.string.login_password_error),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )
        }

        ConditionalNextButton(
//            enabled = isPasswordValid,
            enabled = true,
            onClick = onNext,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(
    name = "RegisterStep3Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep3ScreenPreview() {
    DefaultWhiteTheme {
        RegisterStep3Screen(
            onNext = {

            },
            onBack = {

            }
        )
    }
}