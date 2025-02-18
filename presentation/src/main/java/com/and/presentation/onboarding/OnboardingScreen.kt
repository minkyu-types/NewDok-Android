package com.and.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.and.presentation.R
import com.and.presentation.ui.whiteColorScheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val pagerState = rememberPagerState() { 3 }

    Column(
        modifier = Modifier
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
            OnboardingRegisterButton(modifier = Modifier)
            OnBoardingLoginText(modifier = Modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
            fontSize = 14.sp,
            color = colorResource(id = R.color.neutral_10),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = subtitleText,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        repeat(3) { index ->
            val isSelected = (pagerState.currentPage == index)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(width = 20.dp, height = 4.dp)
                    .background(
                        color = if (isSelected) colorResource(id = R.color.primary_0)
                        else colorResource(id = R.color.neutral_4),
                        shape = RectangleShape,
                    )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
            0 -> 1
            1 -> 2
            else -> 3
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 40.dp)
                .background(
                    color = when (page) {
                        0 -> colorResource(id = R.color.tint_0)
                        1 -> colorResource(id = R.color.tint_4)
                        else -> colorResource(id = R.color.tint_8)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            // TODO - 이미지 리소스로 변경
            Image(
                painter = painterResource(id = R.drawable.ic_fill_bell),
//                painter = painterResource(id = imageRes),
                contentDescription = "$page 페이지",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun OnboardingRegisterButton(modifier: Modifier) {
    Button(
        onClick = {
            // 회원가입 화면으로 이동
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD9D9D9),
        )
    ) {
        Text(
            text = stringResource(id = R.string.register)
        )
    }
}

@Composable
fun OnBoardingLoginText(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_login_desc),
            fontSize = 12.sp
        )
        Text(
            text = stringResource(id = R.string.login),
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp,
            modifier = Modifier.clickable {
                // 로그인 화면으로 이동
            }
        )
    }
}

@Composable
fun OnboardingTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = whiteColorScheme,
        content = content
    )
}

@Preview(
    name = "OnboardingScreen Preview",
    showBackground = true
)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()

    OnboardingTheme {
        OnboardingScreen(navController)
    }
}