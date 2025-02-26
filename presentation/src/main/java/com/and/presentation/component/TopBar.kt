package com.and.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Heavy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onNavigationIconClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar (
        title = {
            Box(
                modifier = Modifier
                    .height(height = 56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = Body1Normal,
                    fontWeight = FontWeight.Bold,
                    color = Caption_Heavy
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        actions = {
            // 필요한 경우 추가 액션 아이콘 배치
        },
        modifier = Modifier.fillMaxWidth().height(44.dp)
    )
}