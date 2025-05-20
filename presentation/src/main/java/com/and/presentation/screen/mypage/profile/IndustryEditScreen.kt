package com.and.presentation.screen.mypage.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.IndustryCategory
import com.and.newdok.presentation.R
import com.and.presentation.component.IndustryDropDown
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun IndustryEditScreen(
    industry: IndustryCategory?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currIndustry: IndustryCategory? by remember { mutableStateOf(industry) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.profile_edit_industry_title),
            onNavigationIconClick = onBack,
        )
        IndustryDropDown(
            onSelect = { industry ->
                currIndustry = industry
            },
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        ConditionalNextButton(
            enabled = (industry != null),
            onClick = {
                // 닉네임 변경 후 뒤로 가기
                onBack()
            },
            buttonText = stringResource(R.string.edit),
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Preview(
    name = "IndustryEditScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun IndustryEditScreenPreview() {
    DefaultWhiteTheme {
        IndustryEditScreen(
            industry = null,
            onBack = {

            }
        )
    }
}