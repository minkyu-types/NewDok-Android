@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.and.presentation.screen.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.PublicationDay
import com.and.newdok.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.component.item.IndustryChip
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun IndustryAndDayBottomSheet(
    title: String,
    sheetState: SheetState,
    prevIndustryCategory: List<IndustryCategory> = emptyList(),
    prevDayId: List<PublicationDay> = emptyList(),
    onApply: (List<IndustryCategory>, List<PublicationDay>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndustries by remember {
        mutableStateOf(prevIndustryCategory)
    }
    var selectedPublicationDays by remember {
        mutableStateOf(prevDayId)
    }

    val selectableIndustries = remember {
        IndustryCategory.entries.filter { it != IndustryCategory.DEFAULT }
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
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
                            onDismiss()
                        }
                        .height(48.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.industry_category_2),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    selectableIndustries.forEach { industry ->
                        IndustryChip(
                            text = industry.value,
                            initialSelected = industry in selectedIndustries,
                            onSelectionChanged = { isChecked ->
                                selectedIndustries = if (industry == IndustryCategory.ALL_INDUSTRIES) {
                                    if (isChecked) listOf(IndustryCategory.ALL_INDUSTRIES) else emptyList()
                                } else {
                                    val withoutAll = selectedIndustries - IndustryCategory.ALL_INDUSTRIES
                                    if (isChecked) {
                                        withoutAll + industry
                                    } else {
                                        withoutAll - industry
                                    }
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(R.string.publication_day),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                )
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    PublicationDay.entries.forEach { day ->
                        IndustryChip(
                            text = day.value,
                            initialSelected = day in selectedPublicationDays,
                            onSelectionChanged = { isChecked ->
                                val newDays = selectedPublicationDays.toMutableList()
                                if (isChecked) {
                                    newDays.add(day)
                                } else {
                                    newDays.remove(day)
                                }
                                selectedPublicationDays = newDays
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            SolidPrimaryButton(
                buttonText = stringResource(R.string.apply_filter),
                buttonSize = ButtonSize.LARGE,
                onClick = {
                    val finalIndustries = selectedIndustries.ifEmpty {
                        listOf(IndustryCategory.ALL_INDUSTRIES)
                    }
                    onApply(finalIndustries, selectedPublicationDays)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}

@Preview(
    name = "IndustryAndDayBottomSheet Preview",
    showBackground = true
)
@Composable
private fun IndustryAndDayBottomSheetPreview() {
    DefaultWhiteTheme {
        var sheetState = rememberModalBottomSheetState()

        LaunchedEffect(Unit) {
            sheetState.show()
        }

    }
}
