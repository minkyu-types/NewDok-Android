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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Primary_Normal

/**
 * 조건에 따라 enable 여부가 변경되는 버튼
 *
 * 텍스트 색상
 * enabled -> Tint10
 * disabled -> Neutral10
 *
 * @param buttonText "다음"이 아닌 다른 버튼 텍스트를 사용해야 하는 경우
 */
@Composable
fun ConditionalNextButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String = stringResource(id = R.string.next)
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(enabled) Primary_Normal else Line_Disabled,
        )
    ) {
        Text(
            text = buttonText,
            style = Body2Normal,
            fontWeight = FontWeight.Bold,
            color = if (enabled) Color.White
                else Caption_Disabled
        )
    }
}