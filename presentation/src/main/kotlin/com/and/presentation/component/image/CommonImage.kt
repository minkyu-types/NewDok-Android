package com.and.presentation.component.image

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun CommonImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
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