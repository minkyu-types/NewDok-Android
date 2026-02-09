package com.and.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal

/**
 *
 * @param text 버튼에 보여질 텍스트
 * @param icon 텍스트 우측에 보여질 아이콘 리소스
 * @param leastOneItemSelected 최소 1개 이상의 필터 항목이 선택되었는지
 */
@Composable
fun FilterChip(
    text: String,
    icon: Painter,
    leastOneItemSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mainColor = if (leastOneItemSelected) Primary_Normal else Caption_Assistive

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 1.dp,
                color = if (leastOneItemSelected) Primary_Normal else Line_Neutral,
                shape = RoundedCornerShape(100.dp)
            )
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = mainColor
        )
        Icon(
            painter = icon,
            contentDescription = null,
            tint = mainColor,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(
    name = "FilterChip Preview",
    showBackground = true
)
@Composable
fun FilterChipPreview() {
    DefaultWhiteTheme {
        FilterChip(
            text = "인기순",
            icon = painterResource(R.drawable.ic_line_arrow_transfer),
            leastOneItemSelected = true,
            onClick = {

            }
        )
    }
}