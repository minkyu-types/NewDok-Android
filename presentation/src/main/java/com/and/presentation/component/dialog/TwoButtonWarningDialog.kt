package com.and.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedSecondaryButton
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Primary_Normal

/**
 * @author Loki
 * 제목, 내용, 버튼 값을 받고 버튼 클릭 시 닫히는 안내용 다이얼로그
 */
@Composable
fun TwoButtonWarningDialog(
    title: String,
    body: String,
    warning: String,
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
                    Text(
                        text = title,
                        style = Heading2,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Heavy,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = body,
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = warning,
                    style = Label1,
                    fontWeight = FontWeight.Medium,
                    color = Primary_Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(Background_System)
                        .padding(16.dp)
                )
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
    name = "TwoButtonWarningDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun TwoButtonWarningDialogPreview() {
    DefaultWhiteTheme {
        TwoButtonWarningDialog(
            title = "구독 이메일",
            body = "회원가입 시 자동으로 생성되는 뉴스레터 구독을 위한 이메일 주소에요.",
            warning = "뉴독으로 아티클을 받아보기 위해선\n" +
                    "뉴독의 구독 이메일 주소로 구독을 신청해야 해요.\n" +
                    "구독 이메일은 개인적인 용도로 사용하거나\n" +
                    "메일을 보내는 것이 불가능해요.",
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