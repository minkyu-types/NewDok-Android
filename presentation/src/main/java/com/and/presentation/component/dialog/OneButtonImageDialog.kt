package com.and.presentation.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2

/**
 * @author Loki
 *
 * @image painter 이미지
 */
@Composable
fun OneButtonImageDialog(
    image:  Painter,
    title: String,
    body: String,
    buttonText: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 28.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = image,
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = body,
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = Caption_Neutral,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                SolidPrimaryButton(
                    buttonText = buttonText,
                    buttonSize = ButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClick
                )
            }
        }
    }
}

@Preview(
    name = "OneButtonImageDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun OneButtonImageDialogPreview() {
    DefaultWhiteTheme {
        OneButtonImageDialog(
            onDismiss = {

            },
            onClick = {

            },
            image = painterResource(R.drawable.img_update),
            title = "최신 버전 업데이트가 있습니다.",
            body = "안정적인 서비스 사용을 위해\n" +
                    "최신 버전으로 업데이트를 진행해 주세요.",
            buttonText = "업데이트"
        )
    }
}