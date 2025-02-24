package com.and.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import com.and.presentation.R
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Neutral1
import com.and.presentation.ui.Primary0
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
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_terms_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(48.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_1),
                initialChecked = isTerm1Checked,
                onCheckChange = { isTerm1Checked = it }
            )
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_2),
                initialChecked = isTerm2Checked,
                onCheckChange = { isTerm2Checked = it }
            )
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_3),
                initialChecked = isTerm3Checked,
                onCheckChange = { isTerm3Checked = it }
            )
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_4),
                initialChecked = isTerm4Checked,
                onCheckChange = { isTerm4Checked = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
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
            .height(56.dp)
            .background(Color.White)
            .padding(start = 8.dp, end = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        if (initialChecked) Primary0 else Color.White
                    )
                    .border(
                        width = if (initialChecked) 0.dp else 1.dp,
                        shape = RoundedCornerShape(5.dp),
                        color = Color.Gray
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checkmark),
                    contentDescription = null,
                    tint = if (initialChecked) Color.White else Color.Gray
                )
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
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Neutral1)
            .clickable {
                onCheckChange(!initialChecked)
            }
            .padding(start = 8.dp, end = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.register_terms_term_all),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        if (initialChecked) Primary0 else Color.White
                    )
                    .border(
                        width = if (initialChecked) 0.dp else 1.dp,
                        shape = RoundedCornerShape(5.dp),
                        color = Color.Gray
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checkmark),
                    contentDescription = null,
                    tint = if (initialChecked) Color.White else Color.Gray
                )
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