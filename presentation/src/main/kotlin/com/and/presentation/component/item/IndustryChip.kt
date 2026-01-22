package com.and.presentation.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.ui.Caption
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal

@Composable
fun IndustryChip(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = Caption,
    fontWeight: FontWeight = FontWeight.Medium,
    defaultTextColor: Color = Caption_Strong,
    selectedTextColor: Color = Primary_Normal,
    defaultBorderColor: Color = Line_Neutral,
    selectedBorderColor: Color = Primary_Normal,
    onSelectionChanged: ((Boolean) -> Unit)? = null,
) {
    var selected by remember { mutableStateOf(false) }

    Text(
        text = text,
        style = textStyle,
        fontWeight = fontWeight,
        color = if (selected) selectedTextColor else defaultTextColor,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .clickable {
                selected = !selected
                onSelectionChanged?.invoke(selected)
            }
            .border(
                width = 1.dp,
                color = if (selected) selectedBorderColor else defaultBorderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}