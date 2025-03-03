package com.and.presentation.component.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onHideRequested: () -> Unit,
    coroutineScope: CoroutineScope,
    body: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        Button(
            onClick = onHideRequested,
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetDialogPreview() {
    val sheetState = rememberModalBottomSheetState()

    BottomSheetDialog(
        sheetState = sheetState,
        onDismiss = { /* 프리뷰에서는 빈 처리 */ },
        onHideRequested = { /* 프리뷰에서는 빈 처리 */ },
        body = {},
        coroutineScope = rememberCoroutineScope(),
        modifier = Modifier
    )
}