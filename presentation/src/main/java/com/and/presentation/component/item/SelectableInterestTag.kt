package com.and.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.InterestCategory
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun SelectableInterestTag(
    interest: InterestCategory,
    isSelected: Boolean,
    onClick: (InterestCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .removeRippleEffect { onClick(interest) }
            .heightIn(min = 32.dp)
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) Primary_Normal else Line_Neutral,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = Color.White
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = interest.value,
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Primary_Normal else Caption_Strong
        )
    }
}

@Preview(
    name = "SelectableInterestTag Preview",
    showBackground = true
)
@Composable
fun SelectableInterestTagPreview() {
    DefaultWhiteTheme {
        SelectableInterestTag(
            interest = InterestCategory.INTEREST_LIVING_INTERIOR,
            isSelected = false,
            onClick = {

            }
        )
    }
}