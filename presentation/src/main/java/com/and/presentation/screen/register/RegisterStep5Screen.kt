package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.ui.Line_Blue_100
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun RegisterStep5Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isTerm1Checked by remember { mutableStateOf(false) }
    var isTerm2Checked by remember { mutableStateOf(false) }
    var isTerm3Checked by remember { mutableStateOf(false) }
    var isTerm4Checked by remember { mutableStateOf(false) }
    var isAllTermsChecked = isTerm1Checked && isTerm2Checked
            && isTerm3Checked && isTerm4Checked

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
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.register_terms_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_1),
                initialChecked = isTerm1Checked,
                onCheckChange = { isTerm1Checked = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_2),
                initialChecked = isTerm2Checked,
                onCheckChange = { isTerm2Checked = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_3),
                initialChecked = isTerm3Checked,
                onCheckChange = { isTerm3Checked = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_4),
                initialChecked = isTerm4Checked,
                onCheckChange = { isTerm4Checked = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            )
            RegisterTermAgreeAllButton(
                initialChecked = isAllTermsChecked,
                onCheckChange = { newState ->
                    isTerm1Checked = newState
                    isTerm2Checked = newState
                    isTerm3Checked = newState
                    isTerm4Checked = newState
                }
            )
        }

        ConditionalNextButton(
            buttonText = stringResource(R.string.register_terms_complete),
            enabled = isTerm1Checked && isTerm2Checked && isTerm3Checked,
            onClick = onNext,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun RegisterTermAgreeButton(
    title: String,
    initialChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .removeRippleEffect {
                onCheckChange(!initialChecked)
            }
            .fillMaxWidth()
            .background(Color.White)
            .padding(end = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Strong,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .border(
                        width = (1.2).dp,
                        shape = RoundedCornerShape(5.dp),
                        color = if (initialChecked) Line_Blue_100 else Line_Alternative
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (initialChecked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        contentDescription = null,
                        tint = Primary_Normal
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterTermAgreeAllButton(
    initialChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .removeRippleEffect { onCheckChange(!initialChecked) }
            .fillMaxWidth()
            .padding(end = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.register_terms_term_all),
                style = Body1Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Heavy,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        if (initialChecked) Primary_Normal else Color.White
                    )
                    .border(
                        width = if (initialChecked) 0.dp else (1.2).dp,
                        shape = RoundedCornerShape(5.dp),
                        color = Line_Alternative
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (initialChecked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(
    name = "RegisterStep5Screen Preview",
    showBackground = true
)
@Composable
fun RegisterStep5ScreenPreview() {
    DefaultWhiteTheme {
        RegisterStep5Screen(
            onNext = {

            },
            onBack = {

            }
        )
    }
}