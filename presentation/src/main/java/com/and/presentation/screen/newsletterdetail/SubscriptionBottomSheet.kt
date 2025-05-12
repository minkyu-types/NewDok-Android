@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.and.presentation.screen.newsletterdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.WebViewScreen
import com.and.presentation.model.NewsLetterDetailModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Heavy

@Composable
fun SubscriptionBottomSheet(
    newsLetterDetailModel: NewsLetterDetailModel,
    sheetState: SheetState,
    onHideRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    SubscriptionBottomSheetDialog(
        title = "${newsLetterDetailModel.brandName} 구독하기",
        sheetState = sheetState,
        onDismiss = {

        },
        onHideRequested = onHideRequested,
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WebViewScreen(
                    url = newsLetterDetailModel.subscribeUrl
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubscriptionBottomSheetDialog(
    title: String,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onHideRequested: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = title,
                    style = Body1Normal,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy,
                    modifier = Modifier.align(Alignment.Center),
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_line_close),
                    contentDescription = stringResource(id = R.string.close),
                    modifier = Modifier
                        .clickable {
                            onHideRequested()
                        }
                        .height(48.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            content()
        }
    }
}