package com.and.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Primary_Normal

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // 트랙과 썸의 크기 정의
    val trackWidth = 42.dp
    val trackHeight = 24.dp
    val thumbDiameter = 20.dp

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) (trackWidth - thumbDiameter - 2.dp) else 2.dp,
        label = "SwitchThumbOffset"
    )

    Box(
        modifier = modifier
            .width(trackWidth)
            .height(trackHeight)
            .clip(RoundedCornerShape(1200.dp))
            .background(
                if (checked) Primary_Normal
                else Color.LightGray
            )
            .clickable { onCheckedChange(!checked) }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_switch_thumb),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(20.dp)
                .offset(x = thumbOffset)
                .align(Alignment.CenterStart)
                .shadow(elevation = 2.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}

@Preview(
    name = "CustomSwitch Preview",
    showBackground = true
)
@Composable
fun CustomSwitchPreview() {
    var isChecked by remember { mutableStateOf(false) }
    CustomSwitch(
        checked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}