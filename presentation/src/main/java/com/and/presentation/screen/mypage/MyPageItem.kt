package com.and.presentation.screen.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong

@Composable
fun MyPageItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.ic_line_right),
            contentDescription = null,
            tint = Caption_Neutral
        )
    }
}