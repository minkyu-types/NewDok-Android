package com.and.presentation.component.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Heavy

@Composable
fun MainTopBar(
    title: String,
    onSearchClick: () -> Unit,
    onAlarmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onSearchClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_line_search),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
        IconButton(
            onClick = onAlarmClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_line_bell),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}