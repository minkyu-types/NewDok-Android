package com.and.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.screen.login.LoginTheme

@Composable
fun PhoneAuthTextField(
    value: String,
    isError: Boolean,
    onAuthButtonClick: (String) -> Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var buttonText by remember { mutableStateOf("인증요청") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(Color.White),
    ) {
        Text(
            text = stringResource(id = R.string.phone_number_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.register_phone_auth_placeholder),
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_mobile),
                    contentDescription = null,
                )
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        val result = onAuthButtonClick(value)
                        // 인증요청 결과가 "이미 가입된 정보가 있다"면, 팝업 보여주기
                        if (result) {
                            // 인증요청 성공 시
                            buttonText = "재전송"
                        }
                    },
                    modifier = Modifier
                        .height(32.dp),
                    shape = RoundedCornerShape(30.dp),
                    contentPadding = PaddingValues(
                        start = 15.dp,  // padding-left: 15px
                        top = 4.dp,     // padding-top: 4px
                        end = 15.dp,    // padding-right: 15px
                        bottom = 4.dp   // padding-bottom: 4px
                    ),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFFF1F1F1)
                    ),
                ) {
                    Text(
                        text = buttonText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp,        // line-height: 20px
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
            }
        )

        if (showDialog) {
            // 이미 가입된 정보라면 다이얼로그 보여주기
        }
    }
}

@Preview(
    name = "PhoneAuthTextField Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun PhoneAuthTextFieldPreview() {
    LoginTheme {
        PhoneAuthTextField(
            value = "",                // 예시로 비어 있는 상태
            isError = false,           // 에러 상태 off
            onAuthButtonClick = { true },   // 클릭 시 동작 없음
            onValueChange = { }        // 입력값 변경 없음
        )
    }
}