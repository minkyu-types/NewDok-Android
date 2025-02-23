package com.and.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.screen.login.LoginTheme

@Composable
fun ProgressTopBar(
    title: String,
    currentProgress: Int,
    maxProgress: Int,
    onNavigationIconClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // TopBar 하단에 진행 상태를 나타내는 ProgressBar가 존재해야 함
    val progressFraction = if (maxProgress > 0) {
        currentProgress.toFloat() / maxProgress
    } else {
        0f
    }

    Column(modifier = modifier) {
        // 실제 TopBar (예: 타이틀, 뒤로가기 버튼 등)
        // Text("제목") 등 필요한 UI 요소 배치

        // 하단에 Progress 표시
        TopBar(
            title = title,
            onNavigationIconClick = onNavigationIconClick
        )
        LinearProgressIndicator(
            progress = { progressFraction },
            modifier = Modifier.fillMaxWidth().height(2.dp),
            color = colorResource(id = R.color.primary_0),
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
            maxProgress = 5,
            onNavigationIconClick = {

            }
        )
    }
}