package com.and.presentation.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Error_Caption
import com.and.presentation.ui.Error_Fill
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal

/**
 * 힌트, 에러, 마스킹을 지원하는 TextField
 */
@Composable
fun HintErrorSecureTextField(
    icon: Int? = null,
    value: String,
    valueTitle: String,
    valueHint: String,
    isError: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
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
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = valueHint,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Assistive
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(4.dp),
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Primary_Normal,
                unfocusedIndicatorColor = Line_Alternative,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Error_Fill,
                cursorColor = Primary_Normal
            ),
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None // 보이기
            } else {
                PasswordVisualTransformation() // 마스킹
            },
            leadingIcon = icon?.let {
                {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                    )
                }
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = errorMessage,
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Error_Caption,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
        }
    }
}