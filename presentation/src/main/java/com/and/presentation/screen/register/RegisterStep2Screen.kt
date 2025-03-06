package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Disabled
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Neutral5
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.ID_MAX_LENGTH
import com.and.presentation.util.ID_MIN_LENGTH

@Composable
fun RegisterStep2Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    onCheckDuplicate: () -> Boolean,
    modifier: Modifier = Modifier
) {
    var userId by remember { mutableStateOf("") }
    val isIdValid = userId.length in ID_MIN_LENGTH..ID_MAX_LENGTH
    var isIdNotDuplicated by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_id_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.id),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HintErrorTextField(
                    maxLength = ID_MAX_LENGTH,
                    icon = R.drawable.ic_line_user,
                    value = userId,
                    onValueChange = {
                        if (isIdNotDuplicated) isIdNotDuplicated = false
                        userId = it
                    },
                    valueHint = stringResource(id = R.string.register_id_placeholder),
                    isError = userId.isNotBlank() && !isIdValid,
                    modifier = Modifier
                        .weight(1f)
                )
                Button(
                    onClick = {
                        val result = onCheckDuplicate()
                        // 중복이 아닌 경우 TextField border Primary0으로 색상 변경
                        // 중복이라면 중복 확인 다시 클릭하도록 error 발생시켜주기
                        isIdNotDuplicated = result
                    },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(56.dp)
                        .align(Alignment.Bottom)
                        .border(
                            width = if (isIdNotDuplicated) 0.dp else 1.dp,
                            shape = RoundedCornerShape(5.dp),
                            color = Line_Disabled
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                )
                {
                    Text(
                        text = "중복 확인",
                        style = Body2Normal,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Disabled,
                        maxLines = 1
                    )
                }
            }

            if (userId.isNotBlank()) {
                val message: String
                val messageColor: Color

                when {
                    !isIdValid -> {
                        message = stringResource(R.string.register_id_error)
                        messageColor = Color.Red
                    }
                    !isIdNotDuplicated -> {
                        message = stringResource(R.string.register_id_duplicate_check)
                        messageColor = Neutral5
                    }
                    else -> {
                        message = stringResource(R.string.register_id_valid)
                        messageColor = Primary_Normal
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = message,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 4.dp),
                    color = messageColor
                )
            }
        }

        ConditionalNextButton(
//            enabled = isIdValid && isIdNotDuplicated,
            enabled = true,
            onClick = onNext,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(
    name = "RegisterStep2Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep2ScreenPreview() {
    DefaultWhiteTheme {
        RegisterStep2Screen(
            onNext = {

            },
            onBack = {

            },
            onCheckDuplicate = {
                true
            }
        )
    }
}