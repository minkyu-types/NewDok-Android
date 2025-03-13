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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
fun WithdrawalStep1Screen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
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
                text = stringResource(R.string.withdrawal_title, "구구루빵뿜"),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.withdrawal_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.height(48.dp))
            WithdrawalInfos(
                newsLetterCount = 8,
                articleCount = 21,
            )
            Spacer(modifier = Modifier.height(48.dp))
            WithdrawalAgreeCheckbox(
                isChecked = isChecked,
                onCheckChange = {
                    isChecked = !isChecked
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            ConditionalNextButton(
                enabled = isChecked,
                onClick = {
                    onBack()
                },
                buttonText = stringResource(R.string.continue_process),
            )
        }
    }
}

@Composable
private fun WithdrawalInfos(
    newsLetterCount: Int,
    articleCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(horizontal = 4.dp)
    ) {
        Row(

        ) {
            Text(
                text = stringResource(R.string.withdrawal_subscribed_title),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.count, newsLetterCount),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Primary_Normal,
            )
        }
        Row(

        ) {
            Text(
                text = stringResource(R.string.withdrawal_recieved_articles_title),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.count, articleCount),
                style = Body1Normal,
                fontWeight = FontWeight.Bold,
                color = Primary_Normal,
            )
        }
    }
}

@Composable
private fun WithdrawalAgreeCheckbox(
    isChecked: Boolean,
    onCheckChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
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
            text = stringResource(R.string.withdrawal_agreement),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Heavy,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    name = "WithdrawalStep1Screen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun WithdrawalStep1ScreenPreview() {
    DefaultWhiteTheme {
        WithdrawalStep1Screen(
            onBack = {

            }
        )
    }
}