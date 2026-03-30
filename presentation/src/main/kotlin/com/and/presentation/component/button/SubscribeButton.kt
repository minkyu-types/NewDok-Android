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
import com.and.newdok.presentation.R
import com.and.presentation.ui.Caption
import com.and.presentation.model.SubscriptionStatus
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun SubscribeButton(
    subscriptionStatus: SubscriptionStatus,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonText = when (subscriptionStatus) {
        SubscriptionStatus.CONFIRMED -> stringResource(R.string.subscribe_ing)
        SubscriptionStatus.PAUSED -> stringResource(R.string.subscribe_resume)
        else -> stringResource(R.string.subscribe_initial)
    }
    val isInitialOrCheck = subscriptionStatus == SubscriptionStatus.INITIAL || subscriptionStatus == SubscriptionStatus.CHECK
    val backgroundColor = when {
        isInitialOrCheck -> Primary_Normal
        subscriptionStatus == SubscriptionStatus.CONFIRMED -> Line_Neutral
        else -> Color.White
    }
    val textColor = when {
        isInitialOrCheck -> Color.White
        else -> Primary_Normal
    }
    val borderWidth = when {
        isInitialOrCheck || subscriptionStatus == SubscriptionStatus.CONFIRMED -> 0.dp
        else -> 1.dp
    }
    val borderColor = when {
        isInitialOrCheck || subscriptionStatus == SubscriptionStatus.CONFIRMED -> Color.Transparent
        else -> Line_Neutral
    }

    Box(
        modifier = modifier
            .removeRippleEffect { onSubscribeClick() }
            .height(26.dp)
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .background(color = backgroundColor)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = Caption,
            fontWeight = FontWeight.Medium,
            color = textColor
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
            subscriptionStatus = SubscriptionStatus.CONFIRMED,
            onSubscribeClick = {

            }
        )
    }
}