package com.and.presentation.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.util.removeRippleEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit = {},
    onActionButtonClick: () -> Unit = {},
    actionIcon: Painter? = null
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
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .removeRippleEffect { onNavigationIconClick() }
                    .height(56.dp)
                    .padding(horizontal = 12.dp)
            )
        },
        actions = {
            if (actionIcon != null) {
                Icon(
                    painter = actionIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .removeRippleEffect { onActionButtonClick() }
                        .height(56.dp)
                        .padding(horizontal = 12.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
    )
}

@Preview(
    name = "TopBar Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun TopBarPreview() {
    DefaultWhiteTheme {
        TopBar(
            title = "회원가입",
            onNavigationIconClick = {

            },
            onActionButtonClick = {

            },
            actionIcon = painterResource(R.drawable.ic_line_search)
        )
    }
}