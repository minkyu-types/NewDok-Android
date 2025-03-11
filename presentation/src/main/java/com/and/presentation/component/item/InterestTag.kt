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

@Composable
fun InterestTag(
    interest: InterestCategory,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .heightIn(min = 32.dp)
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = Color.White.copy(alpha = 0.61f),
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = interest.value,
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong
        )
    }
}

@Preview(
    name = "InterestTag Preview",
    showBackground = true
)
@Composable
fun InterestTagPreview() {
    DefaultWhiteTheme {
        InterestTag(
            interest = InterestCategory.INTEREST_LIVING_INTERIOR,
        )
    }
}