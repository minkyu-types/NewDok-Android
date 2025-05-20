package com.and.presentation.screen.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.ui.Tint10
import com.and.presentation.util.NICKNAME_MAX_LENGTH
import com.and.presentation.util.nicknameValidation
import com.and.presentation.util.removeRippleEffect
import com.and.domain.model.type.Gender
import com.and.newdok.presentation.R
import java.time.LocalDate

@Composable
fun RegisterStep4Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userNickname by rememberSaveable { mutableStateOf("") }
    val isNicknameValid = userNickname.nicknameValidation()
    val years = (1970..LocalDate.now().year.minus(12)).map { it.toString() }
    var userGender by rememberSaveable { mutableStateOf<Gender?>(null) }
    var selectedItem: String? by rememberSaveable { mutableStateOf(null) }

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
                years = years,
                selectedItem = selectedItem,
                onSelect = { year ->
                    selectedItem = year
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
    years: List<String>,
    selectedItem: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var yearBoxSize by remember { mutableStateOf(Size.Zero) }
    var dropdownExpanded by remember { mutableStateOf(false) }

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
                .removeRippleEffect { dropdownExpanded = !dropdownExpanded }
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = Line_Alternative,
                    shape = RoundedCornerShape(4.dp)
                )
                .onGloballyPositioned { coordinates ->
                    yearBoxSize = coordinates.size.toSize()
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .height(48.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem ?: stringResource(R.string.select),
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = if (selectedItem == null) Caption_Assistive
                    else Caption_Strong,
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
        BirthYearDropDown(
            expanded = dropdownExpanded,
            boxSize = yearBoxSize,
            selectedItem = selectedItem,
            years = years,
            onYearSelect = { year ->
                onSelect(year)
                dropdownExpanded = false
            }
        )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BirthYearDropDown(
    expanded: Boolean,
    selectedItem: String?,
    boxSize: Size,
    years: List<String>,
    onYearSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {

            },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        boxSize.width.toDp()
                    }
                )
                .heightIn(max = 240.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White),
        ) {
            years.forEach { year ->
                BirthYearDropDownItem(
                    selectedItem,
                    year,
                    onSelect = { yearItem ->
                        onYearSelect(yearItem)
                    }
                )
            }
        }
    }
}

@Composable
fun BirthYearDropDownItem(
    selectedItem: String?,
    currentItem: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            Text(
                text = currentItem,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = if (selectedItem == currentItem)
                    Primary_Normal
                else
                    Caption_Strong,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        },
        onClick = {
            onSelect(currentItem)
        },
        modifier = Modifier
            .height(48.dp)
            .background(
                if (selectedItem == currentItem) Tint10
                else Color.White
            )
    )
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
                    .height(48.dp)
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
                    .height(48.dp)
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