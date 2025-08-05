package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun TermsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.service_feedback),
            onNavigationIconClick = onBack,
        )
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 24.dp)
        ) {
            MyPageItem(
                text = stringResource(R.string.terms_service),
                onClick = {

                }
            )
            MyPageItem(
                text = stringResource(R.string.terms_personal),
                onClick = {

                }
            )
        }
    }
}

@Preview(
    name = "TermsScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun TermsScreenPreview() {
    DefaultWhiteTheme {
        TermsScreen(
            onBack = {

            }
        )
    }
}