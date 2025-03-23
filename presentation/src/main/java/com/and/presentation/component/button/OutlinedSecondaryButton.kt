package com.and.presentation.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Line_Neutral

@Composable
fun OutlinedSecondaryButton(
    buttonText: String,
    buttonSize: ButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    enabled: Boolean = true
) {
    val textColor = if (enabled) Caption_Neutral else Caption_Disabled
    val borderColor = if (enabled) Line_Neutral else Line_Disabled
    val (verticalPadding, horizontalPadding) = when (buttonSize) {
        ButtonSize.SMALL -> Pair(6, 14)
        ButtonSize.MEDIUM -> Pair(10, 22)
        ButtonSize.LARGE -> Pair(14, 28)
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(vertical = verticalPadding.dp, horizontal = horizontalPadding.dp),
        modifier = modifier
            .heightIn(min = 32.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = buttonText,
            style = Body2Normal,
            fontWeight = fontWeight,
            color = textColor,
        )
    }
}