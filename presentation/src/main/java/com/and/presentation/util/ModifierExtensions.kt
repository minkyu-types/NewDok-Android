package com.and.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.removeRippleEffect(
    crossinline onClick: () -> Unit
): Modifier = this.composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = {
            onClick()
        }
    )
}