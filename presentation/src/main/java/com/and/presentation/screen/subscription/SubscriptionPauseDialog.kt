package com.and.presentation.screen.subscription

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.and.newdok.presentation.R
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
 */
@Composable
fun SubscriptionPauseDialog(
    brandName: String,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier
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
                    Text(
                        text = stringResource(R.string.subscribe_pause_title, brandName),
                        style = Heading2,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Heavy,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(R.string.subscribe_pause_body),
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.subscribe_pause_info),
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
                        buttonText = stringResource(R.string.cancel),
                        buttonSize = ButtonSize.LARGE,
                        modifier = Modifier.weight(1f),
                        onClick = onLeftButtonClick
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SolidPrimaryButton(
                        buttonText = stringResource(R.string.subscribe_paused),
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
        SubscriptionPauseDialog(
            brandName = "뉴닉",
            onLeftButtonClick = {

            },
            onRightButtonClick = {

            },
            onDismiss = {

            }
        )
    }
}