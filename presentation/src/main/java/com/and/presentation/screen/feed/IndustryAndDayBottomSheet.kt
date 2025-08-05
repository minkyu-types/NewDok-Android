@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.and.presentation.screen.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.and.presentation.component.item.CategoryChip
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
    onDismiss: (List<IndustryCategory>, List<PublicationDay>) -> Unit,
    onHideRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndustries by remember {
        mutableStateOf(
            prevIndustryCategory
        )
    }
    var selectedPublicationDays by remember {
        mutableStateOf(
            prevDayId
        )
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss(selectedIndustries, selectedPublicationDays)
        },
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        Column(
            modifier =
            Modifier.fillMaxHeight(0.5f)
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
            FlowRow (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                IndustryCategory.entries.forEach { industry ->
                    IndustryChip(
                        text = industry.value,
                        onSelectionChanged = { isChecked ->
                            val newIndustries = selectedIndustries.toMutableList()
                            if (isChecked) {
                                newIndustries.add(industry)
                            } else {
                                newIndustries.remove(industry)
                            }
                            selectedIndustries = newIndustries
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
            FlowRow (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                PublicationDay.entries.forEach { day ->
                    IndustryChip(
                        text = day.value,
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