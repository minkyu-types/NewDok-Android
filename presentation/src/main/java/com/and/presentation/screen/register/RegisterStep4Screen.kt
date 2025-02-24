package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.component.HintErrorTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary0
import com.and.presentation.util.NICKNAME_MAX_LENGTH
import com.and.presentation.util.nicknameValidation
import com.and.presentation.util.type.Gender

@Composable
fun RegisterStep4Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userNickname by remember { mutableStateOf("") }
    val isNicknameValid = userNickname.nicknameValidation()
    val isBirthYearSelected by remember { mutableStateOf(false) }
    var userGender by remember { mutableStateOf<Gender?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_nickname_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(48.dp))
            HintErrorTextField(
                maxLength = NICKNAME_MAX_LENGTH,
                value = userNickname,
                onValueChange = { userNickname = it },
                valueTitle = stringResource(id = R.string.nickname),
                valueHint = stringResource(id = R.string.register_nickname_placeholder),
                isError = userNickname.isNotBlank() && !isNicknameValid,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            if (userNickname.isNotBlank() && !isNicknameValid) {
                Text(
                    text = stringResource(R.string.register_nickname_placeholder),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 6.dp, start = 6.dp),
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            RegisterBirthYear(
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            RegisterGenderRadioGroup(
                userGender = userGender,
                onClick = { userGender = it }
            )
        }

        ConditionalNextButton(
            enabled = isNicknameValid && isBirthYearSelected && (userGender != null),
            onClick = onNext,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun RegisterBirthYear(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(R.string.birth_year),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            contentPadding = PaddingValues(start = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.select),
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.register_birth_year_desc),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

@Composable
fun RegisterGenderRadioGroup(
    userGender: Gender?,
    onClick: (Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(R.string.gender),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Button(
                onClick = { onClick(Gender.MALE) },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (userGender == Gender.MALE) Primary0
                    else Color(0xFFD9D9D9),
                )
            ) {
                Text(
                    text = stringResource(R.string.gender_male),
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = { onClick(Gender.FEMALE) },
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (userGender == Gender.FEMALE) Primary0
                    else Color(0xFFD9D9D9),
                )
            ) {
                Text(
                    text = stringResource(R.string.gender_female),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(
    name = "RegisterStep4Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep4ScreenPreview() {
    DefaultWhiteTheme {
        RegisterStep4Screen(
            onNext = {

            },
            onBack = {

            }
        )
    }
}