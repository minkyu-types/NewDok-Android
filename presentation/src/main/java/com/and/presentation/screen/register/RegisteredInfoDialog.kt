package com.and.presentation.screen.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.and.presentation.model.RegisteredUser
import com.and.presentation.screen.login.LoginTheme
import com.and.presentation.util.toLocalDateWithDot
import com.and.presentation.util.toMaskedString
import java.time.LocalDate

/**
 * @author Loki
 * 회원 가입 시 해당 전화번호로 가입된 계정이 있는 경우 표시되는 다이얼로그
 * 1개의 전화번호로 최대 3개의 계정을 생성할 수 있음
 */
@Composable
fun RegisterFailDialog(
    onDismiss: () -> Unit,
    registeredUsers: List<RegisteredUser>,
    onContinue: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isUserLessThanThree = registeredUsers.size < 3

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "이미 가입된 정보입니다",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 6.dp),
                    ) {
                        items(registeredUsers) { userInfo ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp, horizontal = 12.dp)
                            ) {
                                Text(
                                    text = userInfo.id.toMaskedString(),
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = userInfo.registeredDate.toLocalDateWithDot(),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isUserLessThanThree) "한 번호로 최대 3개의 계정을 만들 수 있어요."
                        else "한 번호로 최대 3개의 계정만 만들 수 있어요.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    TextButton(
                        onClick = onContinue,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = if (isUserLessThanThree) "계속 진행하기"
                            else "ID/FW 찾기",
                            color = Color.Black
                        )
                    }
                    VerticalDivider(
                        modifier = Modifier.width(1.dp),
                        color = Color.LightGray
                    )
                    TextButton(
                        onClick = onLogin,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "로그인",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "RegisterFailDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun RegisterFailDialogPreview() {
    LoginTheme {
        RegisterFailDialog(
            onDismiss = {

            },
            registeredUsers = listOf(
                RegisteredUser("lion9638", LocalDate.now()),
                RegisteredUser("cat1234", LocalDate.now().minusMonths(2)),
                RegisteredUser("dog6789", LocalDate.now().minusMonths(12)),
            ),
            onContinue = {

            },
            onLogin = {

            }
        )
    }
}