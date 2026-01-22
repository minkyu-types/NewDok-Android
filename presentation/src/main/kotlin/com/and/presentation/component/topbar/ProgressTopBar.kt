package com.and.presentation.component.topbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.screen.login.LoginTheme
import com.and.presentation.ui.Primary_Normal

@Composable
fun ProgressTopBar(
    title: String,
    currentProgress: Int,
    maxProgress: Int,
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit = {}
) {
    val progressFraction: Float = if (maxProgress > 0) {
        currentProgress.toFloat() / maxProgress
    } else {
        1f
    }

    Column {
        TopBar(
            title = title,
            onNavigationIconClick = onNavigationIconClick,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        )
        LinearProgressBar(
            progressFraction = progressFraction,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            progressColor = Primary_Normal,
        )
    }
}

@Composable
fun LinearProgressBar(
    progressFraction: Float,
    modifier: Modifier = Modifier,
    progressColor: Color = Color.Blue,
    trackColor: Color = Color.LightGray
) {
    Canvas(modifier = modifier) {
        val progressWidth = (size.width * progressFraction).toInt().toFloat()

        drawRect(
            color = progressColor,
            topLeft = Offset(0f, 0f),
            size = Size(progressWidth, size.height)
        )

        drawRect(
            color = trackColor,
            topLeft = Offset(progressWidth, 0f),
            size = Size(size.width - progressWidth, size.height)
        )
    }
}

@Preview(
    name = "ProgressTopBar Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun ProgressTopBarPreview() {
    LoginTheme {
        ProgressTopBar(
            title = "회원가입",
            currentProgress = 1,
            maxProgress = 6,
            onNavigationIconClick = {

            }
        )
    }
}