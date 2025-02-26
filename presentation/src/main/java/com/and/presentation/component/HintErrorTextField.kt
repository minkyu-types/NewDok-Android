package com.and.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal

/**
 * leadingIcon, 힌트, 에러를 지원하는 TextField
 *
 * 주의: Error 발생 시 보여줄 Text는 호출부에서 구현해야 함
 * 이유: TextField 우측에 추가 요소 배치 시 TextField의 높이가 변경되어 정렬이 풀림
 */
@Composable
fun HintErrorTextField(
    maxLength: Int,
    icon: Int? = null,
    value: String,
    valueTitle: String,
    valueHint: String,
    onValueChange: (String) -> Unit = {},
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = valueTitle,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            },
            placeholder = {
                Text(
                    text = valueHint,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Assistive
                )
            },
            leadingIcon = icon?.let {
                {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Primary_Normal,
                unfocusedIndicatorColor = Line_Alternative,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White
            ),
        )
    }
}