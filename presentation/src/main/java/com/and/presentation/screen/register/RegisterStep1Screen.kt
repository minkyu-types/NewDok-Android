package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.component.PhoneAuthTextField
import com.and.presentation.ui.whiteColorScheme
import kotlinx.coroutines.delay

/**
 * (1) 사용자 전화번호를 입력받고
 * (2) 해당 전화번호로 인증 문자를 전송하고
 * (3) 입력 받은 인증 문자를 검증하는 화면
 */
@Composable
fun RegisterStep1Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var phoneNumber by remember { mutableStateOf("") }
    var authCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_phone_auth_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(48.dp))

            PhoneAuthTextField(
                value = phoneNumber,
                isError = false,
                onAuthButtonClick = {
                    // 인증 요청
                    true
                },
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { phoneNumber = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AuthTextField(
                value = authCode,
                isError = false,
                onValueChange = { authCode = it }
            )
        }

        NextButton(
            enabled = false,
            onClick = {
                // 다음 단계로 이동
            },
        )
    }
}

@Composable
fun AuthTextField(
    value: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var timeLeft by remember { mutableIntStateOf(180) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = modifier
            .background(Color.White),
    ) {
        Text(
            text = stringResource(id = R.string.auth_number),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                val digitsOnly = newValue.filter { it.isDigit() }
                val limitedValue = digitsOnly.take(6)
                onValueChange(limitedValue)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.register_phone_auth_number_placeholder),
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White
            ),
            trailingIcon = {
                Text(
                    text = formattedTime,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(6.dp))

        if (!isError) {
            Text(
                text = stringResource(id = R.string.register_phone_auth_number_hint),
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        } else {
            // TODO - 인증번호가 틀린 경우 -> 인증번호를 다시 확인해주세요
            Text(
                text = stringResource(id = R.string.register_phone_auth_number_error_1),
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            // 인증 시간이 만료된 경우 -> 인증번호를 재전송해주세요
        }
    }
}

@Composable
fun NextButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .height(56.dp),
        shape = RoundedCornerShape(15.dp),
//        border = BorderStroke(1.dp, color = Color.Gray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD9D9D9),
        )
    ) {
        Text(
            text = stringResource(id = R.string.next),
            color = colorResource(id = R.color.neutral_10),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun RegisterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = whiteColorScheme,
        content = content
    )
}

@Preview(
    name = "RegisterStep1Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep1ScreenPreview() {
    RegisterTheme {
        RegisterStep1Screen(
            onNext = {
                
            },
            onBack = {

            }
        )
    }
}