package com.and.presentation.component.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.and.presentation.ui.DefaultWhiteTheme

/**
 * @param imageUrl 서버가 이미지를 주지 않는 경우가 있어 nullable이다.
 * @param placeholderRes 로딩 중·실패·URL이 없을 때 보여줄 기본 이미지.
 *                       지정하지 않으면 아무것도 그리지 않는다.
 */
@Composable
fun CommonImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes placeholderRes: Int? = null
) {
    val placeholder = placeholderRes?.let { painterResource(id = it) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = placeholder,
        // error: 로드 실패, fallback: imageUrl이 null인 경우
        error = placeholder,
        fallback = placeholder,
        modifier = modifier,
    )
}

@Preview(
    name = "CommonImage Preview",
    showBackground = true
)
@Composable
fun CommonImagePreview() {
    DefaultWhiteTheme {
        CommonImage(
            imageUrl = "https://images.app.goo.gl/jNbVCaTX5AA6kQZv8",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}
