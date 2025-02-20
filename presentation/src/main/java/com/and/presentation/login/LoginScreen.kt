package com.and.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.and.presentation.R
import com.and.presentation.component.HintErrorSecureTextField
import com.and.presentation.component.HintErrorTextField
import com.and.presentation.component.TopBar
import com.and.presentation.ui.whiteColorScheme

/**
 * @author Loki
 * Gemini 문서 작성해주는 기능 개꿀이당
 *
 * 이 화면의 동작은 [LoginViewModel]에 의해 제어되며, 로그인 로직을 처리하고
 * 로그인 시도 실패 여부에 대한 상태를 제공합니다.
 * @see LoginViewModel
 */
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginFailed: Boolean by viewModel.loginFailed // id 오류인지, pw 오류인지 구분하도록
    val userId by remember { mutableStateOf("") }
    val userPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            title = stringResource(id = R.string.login),
            onNavigationIconClick = {
                navController.popBackStack()
            },
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            LoginLogoView()
            Spacer(modifier = Modifier.height(36.dp))
            HintErrorTextField(
                value = userId,
                valueTitle = stringResource(id = R.string.id),
                valueHint = stringResource(id = R.string.login_id_hint),
                isError = loginFailed,
                errorMessage = stringResource(id = R.string.login_id_error),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )
            HintErrorSecureTextField(
                value = userPassword,
                valueTitle = stringResource(id = R.string.password),
                valueHint = stringResource(id = R.string.login_password_hint),
                isError = loginFailed,
                errorMessage = stringResource(id = R.string.login_password_error),
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth()
            )
            LoginFindIdPassword(
                modifier = Modifier.align(Alignment.End)
            )
        }
        LoginButton(
            modifier = Modifier
                .offset(y = (-12).dp)
        )
        LoginBottomText(
            modifier = Modifier,
            onNotMemberClick = {
                // 비회원으로 이용하기
            },
            onRegisterClick = {
                // 로그인
            }
        )
    }
}

@Composable
fun LoginFindIdPassword(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.login_find_id_password),
        textAlign = TextAlign.End,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 8.dp)
            .clickable {
            // 클릭 시 아이디/비밀번호 찾기 화면으로 이동
        },
    )
}

// TODO - 로고 이미지로 변경
@Composable
fun LoginLogoView(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_fill_bell),
        contentDescription = stringResource(id = R.string.logo_image),
        modifier = Modifier
            .padding(horizontal = 16.dp)
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
            color = colorResource(id = R.color.neutral_10),
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
            .padding(top = 12.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.login_without_register),
            fontSize = 14.sp,
            modifier = Modifier.clickable(onClick = onNotMemberClick)
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
            fontSize = 14.sp,
            modifier = Modifier.clickable(onClick = onRegisterClick)
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
        LoginScreen(navController)
    }
}