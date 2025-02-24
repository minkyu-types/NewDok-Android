package com.and.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.ui.Primary0

/**
 * 힌트, 에러를 지원하는 TextField
 *
 * 주의: Error 발생 시 보여줄 Text는 호출부에서 구현해야 함
 * 이유: TextField 우측에 추가 요소 배치 시 TextField의 높이가 변경되어 정렬이 풀림
 */
@Composable
fun HintErrorTextField(
    maxLength: Int,
    value: String,
    valueTitle: String,
    valueHint: String,
    onValueChange: (String) -> Unit = {},
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ){
        Text(
            text = valueTitle,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 6.dp)
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
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color.Red else Primary0,
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray,
                errorBorderColor = Color.Red,
                cursorColor = Primary0
            )
        )
    }
}