package com.and.presentation.screen.mypage.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.Interest
import com.and.newdok.presentation.R
import com.and.presentation.component.InvestigationInterestList
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Headline

@Composable
fun InterestEditScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileEditViewModel
) {
    val interestOptions by viewModel.interestOptions.collectAsState()
    val selectedInterests = rememberSaveable { mutableStateOf(emptySet<Interest>()) }

    LaunchedEffect(Unit) {
        viewModel.getInterests()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.profile_edit_interest_title),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(R.string.interest_category),
                style = Headline,
                fontWeight = FontWeight.Bold,
                color = Caption_Strong,
            )
            Text(
                text = stringResource(R.string.profile_edit_interest_body)
            )
        }
        InvestigationInterestList(
            interests = interestOptions,
            selectedInterests = selectedInterests.value,
            onInterestClick = { interest ->
                val updated = selectedInterests.value.toMutableSet()
                if (interest in updated) {
                    updated.remove(interest)
                } else {
                    updated.add(interest)
                }
                selectedInterests.value = updated
            },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        )
        ConditionalNextButton(
            enabled = selectedInterests.value.isNotEmpty(),
            onClick = {
                viewModel.updateInterests(interests = selectedInterests.value)
                onBack()
            },
            buttonText = stringResource(R.string.edit),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 20.dp, start = 24.dp, end = 24.dp),
        )
    }
}

@Preview(
    name = "InterestEditScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun InterestEditScreenPreview() {
    DefaultWhiteTheme {
        InterestEditScreen(
            onBack = {

            },
            viewModel = hiltViewModel()
        )
    }
}