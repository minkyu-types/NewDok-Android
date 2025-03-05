package com.and.presentation.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal

@Composable
fun SolidPrimaryButton(
    buttonText: String,
    buttonSize: ButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val textColor = if (enabled) Color.White else Caption_Disabled
    val backgroundColor = if (enabled) Primary_Normal else Line_Neutral
    val (verticalPadding, horizontalPadding) = when (buttonSize) {
        ButtonSize.SMALL -> Pair(6, 14)
        ButtonSize.MEDIUM -> Pair(10, 22)
        ButtonSize.LARGE -> Pair(14, 28)
    }

    Box(
        modifier = modifier
            .clickable { onClick() }
            .heightIn(min = 32.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(vertical = verticalPadding.dp, horizontal = horizontalPadding.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}