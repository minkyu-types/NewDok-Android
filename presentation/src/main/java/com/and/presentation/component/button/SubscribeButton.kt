package com.and.presentation.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.InterestCategory
import com.and.presentation.R
import com.and.presentation.component.item.NewsLetterSubscriptionItem
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Caption
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.ui.Tint80
import com.and.presentation.util.removeRippleEffect

@Composable
fun SubscribeButton(
    isSubscribed: Boolean,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .removeRippleEffect { onSubscribeClick() }
            .height(26.dp)
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 1.dp,
                color = if (isSubscribed) Primary_Normal else Line_Neutral,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = if (isSubscribed) Tint80 else Color.White
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isSubscribed) stringResource(R.string.subscribe_ing)
            else stringResource(R.string.subscribe),
            style = Caption,
            fontWeight = FontWeight.Medium,
            color = if (isSubscribed) Color.White else Primary_Normal
        )
    }
}

@Preview(
    name = "SubscribeButton Preview",
    showBackground = true
)
@Composable
fun SubscribeButtonPreview() {
    DefaultWhiteTheme {
        SubscribeButton(
            isSubscribed = true,
            onSubscribeClick = {

            }
        )
    }
}