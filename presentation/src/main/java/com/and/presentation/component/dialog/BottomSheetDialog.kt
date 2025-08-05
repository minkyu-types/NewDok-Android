package com.and.presentation.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Heavy
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    title: String,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onHideRequested: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                sheetState.expand()
            }
        }
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
                        .clickable { onHideRequested() }
                        .height(48.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetDialogPreview() {
    var sheetState = rememberModalBottomSheetState()

    BottomSheetDialog(
        title = "이용약관",
        sheetState = sheetState,
        onDismiss = { /* 프리뷰에서는 빈 처리 */ },
        onHideRequested = { /* 프리뷰에서는 빈 처리 */ },
        content = {},
        modifier = Modifier
    )
}