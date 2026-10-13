package com.and.presentation.screen.mypage.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.domain.model.Industry
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
    viewModel: ProfileEditViewModel,
    modifier: Modifier = Modifier,
) {
    val industries by viewModel.industries.collectAsStateWithLifecycle()
    val userInfoUiState by viewModel.userInfoUiState
    var currIndustry: Industry? by remember { mutableStateOf(null) }
    var initialIndustry: Industry? by remember { mutableStateOf(null) }

    // 사용자의 현재 종사산업을 즉시 설정 (딜레이 없이)
    LaunchedEffect(userInfoUiState) {
        if (userInfoUiState is com.and.presentation.util.UiState.Success && initialIndustry == null) {
            val userIndustryId = (userInfoUiState as com.and.presentation.util.UiState.Success).data.industryId
            if (userIndustryId != null) {
                val industryCategory = IndustryCategory.getIndustryById(userIndustryId)
                if (industryCategory != null) {
                    val userIndustry = Industry(id = industryCategory.id, name = industryCategory.value)
                    currIndustry = userIndustry
                    initialIndustry = userIndustry
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getIndustries()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.profile_edit_industry_title),
            onNavigationIconClick = onBack,
        )
        IndustryDropDown(
            industries = industries,
            onSelect = { industry ->
                currIndustry = industry
            },
            initialSelectedItem = initialIndustry,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        ConditionalNextButton(
            enabled = (currIndustry != null && currIndustry?.id != -1),
            onClick = {
                viewModel.updateIndustry(requireNotNull(currIndustry))
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

            },
            viewModel = hiltViewModel<ProfileEditViewModel>()
        )
    }
}