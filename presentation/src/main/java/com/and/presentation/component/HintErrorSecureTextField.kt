package com.and.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.ui.Primary_Normal

/**
 * 힌트, 에러, 마스킹을 지원하는 TextField
 */
@Composable
fun HintErrorSecureTextField(
    value: String,
    valueTitle: String,
    valueHint: String,
    onValueChange: (String) -> Unit = {},
    isError: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        Text(
            text = valueTitle,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = valueHint,
                    color = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color.Red else Primary_Normal,
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray,
                errorBorderColor = Color.Red,
                cursorColor = Primary_Normal
            ),
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None // 보이기
            } else {
                PasswordVisualTransformation() // 마스킹
            },
            trailingIcon = {
                val iconRes = if (isPasswordVisible) {
                    R.drawable.ic_line_close_eye // 눈 감은 아이콘
                } else {
                    R.drawable.ic_line_eye     // 눈 뜬 아이콘
                }

                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null
                    )
                }
            }
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }
}