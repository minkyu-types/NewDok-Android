package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.component.WebViewScreen
import com.and.presentation.component.dialog.BottomSheetDialog
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.DefaultWhiteTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var bottomSheetUrl by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        BottomSheetDialog(
            title = stringResource(R.string.terms_common),
            sheetState = sheetState,
            onDismiss = {
                showBottomSheet = false
            },
            onHideRequested = {
                coroutineScope.launch {
                    sheetState.hide()
                }
                showBottomSheet = false
            },
            content = {
                bottomSheetUrl?.let {
                    WebViewScreen(it)
                }
            }
        )
    }

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
                    bottomSheetUrl = context.getString(R.string.terms_service_url)
                    showBottomSheet = true
                }
            )
            MyPageItem(
                text = stringResource(R.string.terms_personal),
                onClick = {
                    bottomSheetUrl = context.getString(R.string.terms_personal_url)
                    showBottomSheet = true
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