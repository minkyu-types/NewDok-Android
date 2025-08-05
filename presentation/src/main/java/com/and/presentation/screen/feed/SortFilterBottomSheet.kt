@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.and.presentation.screen.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.SortCategory
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect
import kotlin.math.min

@Composable
fun SortFilterBottomSheet(
    title: String,
    sheetState: SheetState,
    prevSort: SortCategory?,
    onDismiss: (SortCategory) -> Unit,
    onHideRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSort by remember {
        mutableStateOf(
            prevSort ?: SortCategory.POPULARITY
        )
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss(selectedSort)
        },
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        Column(
            modifier =
            modifier.wrapContentHeight()
        ) {
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
                    modifier = Modifier
                        .align(Alignment.CenterStart),
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
            SortCategory.entries.forEach { category ->
                SortItem(
                    sort = category,
                    isSelected = selectedSort == category,
                    onClick = { sort ->
                        selectedSort = sort
                        onDismiss(selectedSort)
                    }
                )
            }
        }
    }
}

@Composable
private fun SortItem(
    sort: SortCategory,
    isSelected: Boolean,
    onClick: (SortCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .removeRippleEffect {
                onClick(sort)
            }
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = sort.value,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Heavy,
            modifier = Modifier
                .align(Alignment.CenterStart),
        )
        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkmark),
                contentDescription = stringResource(id = R.string.select),
                tint = Primary_Normal,
                modifier = Modifier
                    .height(48.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(
    name = "AllNewsLettersFilterBottomSheet Preview",
    showBackground = true
)
@Composable
private fun AllNewsLettersFilterBottomSheetPreview() {
    DefaultWhiteTheme {
        SortItem(
            sort = SortCategory.POPULARITY,
            isSelected = false,
            onClick = {

            }
        )
    }
}