package com.and.presentation.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.and.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedSecondaryButton
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2

/**
 * @author Loki
 * 제목, 내용, 버튼 값을 받고 버튼 클릭 시 닫히는 안내용 다이얼로그
 *
 * @param body 본문 내용. null인 경우 해당 text와 상부 Spacer를 없앰
 */
@Composable
fun TwoButtonDialog(
    title: String,
    body: String?,
    leftButtonText: String,
    rightButtonText: String,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_warning),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = title,
                        style = Heading2,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Heavy,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (body != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = body,
                            style = Body2Normal,
                            fontWeight = FontWeight.Medium,
                            color = Caption_Neutral,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    OutlinedSecondaryButton(
                        buttonText = leftButtonText,
                        buttonSize = ButtonSize.LARGE,
                        modifier = Modifier.weight(1f),
                        onClick = onLeftButtonClick
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SolidPrimaryButton(
                        buttonText = rightButtonText,
                        buttonSize = ButtonSize.LARGE,
                        modifier = Modifier.weight(1f),
                        onClick = onRightButtonClick
                    )
                }
            }
        }
    }
}

@Preview(
    name = "TwoButtonDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun TwoButtonDialogPreview() {
    DefaultWhiteTheme {
        TwoButtonDialog(
            title = "기기의 알림 설정이 꺼져있어요.",
            body = null,
            leftButtonText = "취소",
            rightButtonText = "설정 변경하기",
            onLeftButtonClick = {

            },
            onRightButtonClick = {

            },
            onDismiss = {

            }
        )
    }
}