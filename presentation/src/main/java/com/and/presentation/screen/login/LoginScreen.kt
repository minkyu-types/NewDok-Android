package com.and.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.and.newdok.presentation.R
import com.and.presentation.component.textfield.HintErrorSecureTextField
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.ui.whiteColorScheme
import com.and.presentation.util.ID_MAX_LENGTH
import com.and.presentation.util.removeRippleEffect

/**
 * @author Loki
 * Gemini 문서 작성해주는 기능 개꿀이당
 *
 * 이 화면의 동작은 [LoginViewModel]에 의해 제어되며, 로그인 로직을 처리하고
 * 로그인 시도 실패 여부에 대한 상태를 제공합니다.
 * @see LoginViewModel
 * @param onLoginSuccess 로그인 성공 시
 * @param onLoginWithoutSignUp 비회원으로 이용 클릭 시
 * @param onRegisterClick 회원가입 클릭 시
 * @param onFindIdPassword 아이디/비밀번호 찾기 클릭 시
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onLoginWithoutSignUp: () -> Unit,
    onRegister: () -> Unit,
    onFindIdPassword: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginSuccess: Boolean? by viewModel.loginSuccess
    var userId by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            title = stringResource(id = R.string.login),
            onNavigationIconClick = {
                onBack()
            },
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            LoginLogoView()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.id),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            HintErrorTextField(
                maxLength = ID_MAX_LENGTH,
                value = userId,
                valueHint = stringResource(id = R.string.login_id_hint),
                isError = loginSuccess == false,
                onValueChange = { id ->
                    userId = id
                },
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            HintErrorSecureTextField(
                value = userPassword,
                valueTitle = stringResource(id = R.string.password),
                valueHint = stringResource(id = R.string.login_password_hint),
                isError = loginSuccess == false,
                errorMessage = stringResource(id = R.string.login_password_error),
                onValueChange = { pw ->
                    userPassword = pw
                },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            LoginFindIdPassword(
                onFindIdPassword = onFindIdPassword,
                modifier = Modifier.align(Alignment.End)
            )
        }
        ConditionalNextButton(
            enabled = true,
            onClick = {
                viewModel.login(
                    id = userId,
                    password = userPassword
                )
            },
            modifier = Modifier.padding(16.dp),
            buttonText = stringResource(R.string.login)
        )
        LoginBottomText(
            modifier = Modifier,
            onNotMemberClick = {
                onLoginWithoutSignUp()
            },
            onRegisterClick = { onRegister() }
        )
    }
}

@Composable
fun LoginFindIdPassword(
    onFindIdPassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.login_find_id_password),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onFindIdPassword()
                },
        )
    }
}

// TODO - 로고 이미지로 변경
@Composable
fun LoginLogoView(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_logo),
        contentDescription = stringResource(id = R.string.logo_image),
        modifier = Modifier
            .padding(vertical = 32.dp)
    )
}

@Composable
fun LoginButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            // 회원가입 화면으로 이동
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD9D9D9),
        )
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = Body2Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Disabled,
            fontSize = 16.sp
        )
    }
}

@Composable
fun LoginBottomText(
    modifier: Modifier = Modifier,
    onNotMemberClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.login_without_register),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier
                .removeRippleEffect { onNotMemberClick() }
        )

        VerticalDivider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(1.dp)
                .height(14.dp),
            color = Color.LightGray
        )

        Text(
            text = stringResource(id = R.string.register),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Primary_Normal,
            modifier = Modifier
                .removeRippleEffect { onRegisterClick() }
        )
    }
}

@Composable
fun LoginTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = whiteColorScheme,
        content = content
    )
}

@Preview(
    name = "LoginScreen Preview",
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    val navController: NavHostController = rememberNavController()

    LoginTheme {
        LoginScreen(
            onLoginSuccess = {

            },
            onLoginWithoutSignUp = {

            },
            onRegister = {

            },
            onFindIdPassword = {

            },
            onBack = {

            }
        )
    }
}