package com.and.presentation.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.and.presentation.screen.login.LoginTheme

/**
 * @author Loki
 * 제목, 내용, 버튼 값을 받고 버튼 클릭 시 닫히는 안내용 다이얼로그
 */
@Composable
fun OneButtonDialog(
    title: String,
    body: String,
    buttonText: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
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
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = body,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = buttonText,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(
    name = "OneButtonDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun OneButtonDialogPreview() {
    LoginTheme {
        OneButtonDialog(
            onDismiss = {

            },
            title = "인증 실패",
            body = "인증 횟수를 초과했습니다\n처음부터 다시 진행해주세요",
            buttonText = "처음으로"
        )
    }
}