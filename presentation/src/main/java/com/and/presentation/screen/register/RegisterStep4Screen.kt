package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.HintErrorTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal
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
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_nickname_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.nickname),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            HintErrorTextField(
                maxLength = NICKNAME_MAX_LENGTH,
                value = userNickname,
                onValueChange = { userNickname = it },
                valueHint = stringResource(id = R.string.register_nickname_placeholder),
                isError = userNickname.isNotBlank() && !isNicknameValid,
            )
            if (userNickname.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                if (!isNicknameValid) {
                    Text(
                        text = stringResource(R.string.register_nickname_placeholder),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = stringResource(R.string.register_nickname_valid),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Primary_Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            RegisterBirthYear(
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            RegisterGenderRadioGroup(
                userGender = userGender,
                onClick = { userGender = it }
            )
        }

        ConditionalNextButton(
//            enabled = isNicknameValid && isBirthYearSelected && (userGender != null),
            enabled = true,
            onClick = onNext,
            modifier = Modifier.padding(24.dp)
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
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = Line_Alternative,
                    shape = RoundedCornerShape(4.dp)
                ),
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.select),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = Caption_Assistive,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_line_down),
                    contentDescription = null,
                    tint = Caption_Assistive
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.register_birth_year_desc),
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
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
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
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
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = if (userGender == Gender.MALE) Primary_Normal
                        else Line_Alternative,
                        shape = RoundedCornerShape(4.dp)
                    ),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.gender_male),
                    style = Body2Normal,
                    fontWeight = FontWeight.Bold,
                    color = if (userGender == Gender.MALE) Primary_Normal
                    else Color.Black,
                )
            }
            Button(
                onClick = { onClick(Gender.FEMALE) },
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(56.dp)
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = if (userGender == Gender.FEMALE) Primary_Normal
                        else Line_Alternative,
                        shape = RoundedCornerShape(4.dp)
                    ),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.gender_female),
                    style = Body2Normal,
                    fontWeight = FontWeight.Bold,
                    color = if (userGender == Gender.FEMALE) Primary_Normal
                    else Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.register_gender_desc),
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
        )
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