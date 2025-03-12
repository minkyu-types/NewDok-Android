package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.ID_MAX_LENGTH
import com.and.presentation.util.phoneNumberValidation
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
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_phone_auth_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))

            PhoneAuthView(
                phoneNumber = phoneNumber,
                authCode = authCode,
                onPhoneNumberChange = { phoneNumber ->

                },
                onAuthCodeChange = { authCode ->

                },
                onAuthButtonClick = { phoneNumber ->

                }
            )
        }

        ConditionalNextButton(
            enabled = true,
            onClick = onNext,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
fun PhoneAuthView(
    phoneNumber: String,
    authCode: String,
    onPhoneNumberChange: (String) -> Unit,
    onAuthCodeChange: (String) -> Unit,
    onAuthButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PhoneTextField(
        phoneNumber = phoneNumber,
        onValueChange = onPhoneNumberChange,
        onAuthButtonClick = { phoneNum ->
            // 전화번호로 인증 문자 전송
            true
        },
        modifier = Modifier
            .fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(32.dp))

    AuthTextField(
        value = authCode,
        isError = false,
        onValueChange = onAuthCodeChange
    )
}

@Composable
fun PhoneTextField(
    phoneNumber: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onAuthButtonClick: (String) -> Boolean
) {
    // 전화번호 정규식 검사
    val isPhoneNumberValid = phoneNumber.phoneNumberValidation()

    Column {
        Text(
            text = stringResource(R.string.phone_number_title),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            HintErrorTextField(
                maxLength = ID_MAX_LENGTH,
                icon = R.drawable.ic_mobile,
                value = phoneNumber,
                onValueChange = { onValueChange(it) },
                valueHint = stringResource(id = R.string.register_phone_auth_placeholder),
                isError = phoneNumber.isNotBlank() && !isPhoneNumberValid,
                modifier = Modifier
                    .weight(1f)
            )
            Button(
                enabled = isPhoneNumberValid,
                onClick = {
                    val result = onAuthButtonClick(phoneNumber)
                    // 중복이 아닌 경우 TextField border Primary0으로 색상 변경
                    // 중복이라면 중복 확인 다시 클릭하도록 error 발생시켜주기

                },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(48.dp)
                    .align(Alignment.Bottom)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(5.dp),
                        color = if (isPhoneNumberValid) Primary_Normal else Line_Disabled
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                contentPadding = PaddingValues(horizontal = 20.dp)
            )
            {
                Text(
                    text = stringResource(R.string.auth_request),
                    style = Body2Normal,
                    fontWeight = FontWeight.Bold,
                    color = if (isPhoneNumberValid) Primary_Normal else Caption_Disabled,
                    maxLines = 1
                )
            }
        }
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
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier
                .padding(start = 4.dp)
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
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Assistive
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = TextFieldDefaults.colors(
                // 인증번호 6자리 (숫자만) 입력되면 Primary_Normal 적용
                focusedIndicatorColor = Primary_Normal,
                unfocusedIndicatorColor = Line_Alternative,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            trailingIcon = {
                Text(
                    text = formattedTime,
                    style = Label1,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Strong,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (!isError) {
            Text(
                text = stringResource(id = R.string.register_phone_auth_number_hint),
                style = Label1,
                color = Caption_Neutral,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        } else {
            // TODO - 인증번호가 틀린 경우 -> 인증번호를 다시 확인해주세요
            Text(
                text = stringResource(id = R.string.register_phone_auth_number_error_1),
                style = Label1,
                color = Caption_Neutral,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
            // 인증 시간이 만료된 경우 -> 인증번호를 재전송해주세요
        }
    }
}

@Preview(
    name = "RegisterStep1Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep1ScreenPreview() {
    DefaultWhiteTheme {
        RegisterStep1Screen(
            onNext = {
                
            },
            onBack = {

            }
        )
    }
}