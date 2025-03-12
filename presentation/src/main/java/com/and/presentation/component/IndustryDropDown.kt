package com.and.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.and.domain.model.type.IndustryCategory
import com.and.presentation.R
import com.and.presentation.screen.preinvestigation.IndustryDropDownItem
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.util.removeRippleEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndustryDropDown(
    onSelect: (IndustryCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem: IndustryCategory? by rememberSaveable { mutableStateOf(null) }
    var industryBoxSize by remember { mutableStateOf(Size.Zero) }
    val industries = IndustryCategory.entries

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.industry_category),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .removeRippleEffect { expanded = !expanded }
                .fillMaxWidth()
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = Line_Alternative,
                    shape = RoundedCornerShape(4.dp)
                )
                .onGloballyPositioned { coordinates ->
                    industryBoxSize = coordinates.size.toSize()
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedItem == null)
                        stringResource(R.string.pre_investigation_industry_dropdown_placeholder)
                    else selectedItem!!.value,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = if (selectedItem == null) Caption_Assistive
                    else Caption_Strong,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_line_down),
                    contentDescription = null,
                    tint = Caption_Assistive
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(
                        with(LocalDensity.current) {
                            industryBoxSize.width.toDp()
                        }
                    )
                    .heightIn(max = 240.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White),
            ) {
                industries.forEach { industry ->
                    IndustryDropDownItem(
                        selectedItem,
                        industry,
                        onSelect = { category ->
                            selectedItem = category
                            onSelect(category)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}