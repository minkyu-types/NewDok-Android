package com.and.presentation.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Blue50
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading1
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun OnboardingScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onAutoLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val loginSuccess: Boolean? by viewModel.loginSuccess
    val pagerState = rememberPagerState() { 3 }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            onAutoLogin()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 100.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingHeader(modifier = Modifier, pagerState = pagerState)
            OnboardingIndicator(modifier = Modifier, pagerState = pagerState)
        }
        OnboardingImageViewPager(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f),
            pagerState
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConditionalNextButton(
                enabled = true,
                onClick = onRegisterClick,
                modifier = Modifier.padding(horizontal = 24.dp),
                buttonText = stringResource(R.string.register)
            )
            OnBoardingLoginText(
                onClick = onLoginClick,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun OnboardingHeader(
    pagerState: PagerState,
    modifier: Modifier
) {
    val currentPage: Int = pagerState.currentPage
    val titleText: String = when (currentPage) {
        0 -> stringResource(id = R.string.onboarding_title_1)
        1 -> stringResource(id = R.string.onboarding_title_2)
        2 -> stringResource(id = R.string.onboarding_title_3)
        else -> ""
    }
    val subtitleText = when (currentPage) {
        0 -> stringResource(id = R.string.onboarding_subtitle_1)
        1 -> stringResource(id = R.string.onboarding_subtitle_2)
        2 -> stringResource(id = R.string.onboarding_subtitle_3)
        else -> ""
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = titleText,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitleText,
            style = Heading1,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun OnboardingIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 24.dp, top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        repeat(3) { index ->
            val isSelected = (pagerState.currentPage == index)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(width = 32.dp, height = 4.dp)
                    .background(
                        color = if (isSelected) Primary_Normal
                        else Blue50,
                        shape = RoundedCornerShape(4.dp),
                    )
            )
        }
    }
}

@Composable
fun OnboardingImageViewPager(
    modifier: Modifier,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->
        val imageRes = when (page) {
            0 -> R.drawable.img_onboarding_1
            1 -> R.drawable.img_onboarding_2
            else -> R.drawable.img_onboarding_3
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 40.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$page 페이지",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun OnBoardingLoginText(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_login_desc),
            style = Body2Normal,
            fontWeight = FontWeight.Normal,
            color = Caption_Assistive
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.login),
            style = Body2Normal,
            fontWeight = FontWeight.Normal,
            color = Primary_Normal,
            modifier = Modifier
                .removeRippleEffect { onClick() }
        )
    }
}

@Preview(
    name = "OnboardingScreen Preview",
    showBackground = true
)
@Composable
fun OnboardingScreenPreview() {
    DefaultWhiteTheme {
        OnboardingScreen(
            onRegisterClick = {

            },
            onLoginClick = {

            },
            onAutoLogin = {

            }
        )
    }
}