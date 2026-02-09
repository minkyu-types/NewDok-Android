package com.and.presentation.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.ui.Caption
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.Line_Neutral

@Composable
fun CategoryChip(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = Caption,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = Caption_Strong,
    borderColor: Color = Line_Neutral
) {
    Text(
        text = text,
        style = textStyle,
        fontWeight = fontWeight,
        color = textColor,
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}