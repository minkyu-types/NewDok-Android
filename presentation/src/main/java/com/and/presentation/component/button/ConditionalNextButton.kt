package com.and.presentation.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.ui.Neutral10
import com.and.presentation.ui.Primary0
import com.and.presentation.ui.Tint10

/**
 * 조건에 따라 enable 여부가 변경되는 버튼
 */
@Composable
fun ConditionalNextButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(enabled) Primary0 else Color(0xFFD9D9D9),
        )
    ) {
        Text(
            text = stringResource(id = R.string.next),
            color = if (enabled) Tint10
                else Neutral10,
            fontSize = 16.sp,
        )
    }
}