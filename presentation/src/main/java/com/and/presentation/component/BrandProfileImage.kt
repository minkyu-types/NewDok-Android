package com.and.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral

@Composable
fun BrandProfileImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = CircleShape
            )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier,
        )
    }
}

@Preview(
    name = "BrandProfileImage Preview",
    showBackground = true
)
@Composable
fun BrandProfileImagePreview() {
    DefaultWhiteTheme {
        BrandProfileImage(
            ""
        )
    }
}