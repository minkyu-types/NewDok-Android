package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal

@Composable
fun WithdrawalStep2Screen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isReason1Checked by remember { mutableStateOf(false) }
    var isReason2Checked by remember { mutableStateOf(false) }
    var isReason3Checked by remember { mutableStateOf(false) }
    var isReasonGuitarChecked by remember { mutableStateOf(false) }

    val isAnyChecked = isReason1Checked || isReason2Checked || isReason3Checked || isReasonGuitarChecked

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.withdrawal),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.withdrawal_reason_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.withdrawal_reason_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.height(48.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WithdrawalReasonCheckbox(
                    text = stringResource(R.string.withdrawal_reason_1),
                    isChecked = isReason1Checked,
                    onCheckChange = {
                        isReason1Checked = !isReason1Checked
                    }
                )
                WithdrawalReasonCheckbox(
                    text = stringResource(R.string.withdrawal_reason_2),
                    isChecked = isReason2Checked,
                    onCheckChange = {
                        isReason2Checked = !isReason2Checked
                    }
                )
                WithdrawalReasonCheckbox(
                    text = stringResource(R.string.withdrawal_reason_3),
                    isChecked = isReason3Checked,
                    onCheckChange = {
                        isReason3Checked = !isReason3Checked
                    }
                )
                WithdrawalReasonCheckbox(
                    text = stringResource(R.string.guitar),
                    isChecked = isReason3Checked,
                    onCheckChange = {
                        isReason3Checked = !isReason3Checked
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ConditionalNextButton(
                enabled = isAnyChecked,
                onClick = {
                    onBack()
                },
                buttonText = stringResource(R.string.withdrawal_button),
            )
        }
    }
}

@Composable
private fun WithdrawalReasonCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckChange()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Primary_Normal,
                uncheckedColor = Line_Alternative,
                checkmarkColor = Color.White
            ),
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Heavy,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    name = "WithdrawalStep2Screen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun WithdrawalStep2ScreenPreview() {
    DefaultWhiteTheme {
        WithdrawalStep2Screen(
            onBack = {

            }
        )
    }
}