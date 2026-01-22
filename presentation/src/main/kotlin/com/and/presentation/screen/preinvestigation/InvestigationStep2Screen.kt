package com.and.presentation.screen.preinvestigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.type.InterestCategory
import com.and.newdok.presentation.R
import com.and.presentation.component.InvestigationInterestList
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun InvestigationStep2Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InvestigationViewModel
) {
    val selectedInterests by viewModel.selectedInterests.collectAsState()

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
                text = stringResource(R.string.pre_investigation_interests_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.pre_investigation_interests_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            InvestigationInterestList(
                selectedInterests = selectedInterests,
                onInterestClick = { interest ->
                    viewModel.toggleInterest(interest)
                }
            )
        }
        ConditionalNextButton(
            enabled = (selectedInterests.size >= 3),
            onClick = {
                viewModel.updateInterests(selectedInterests)
                onNext()
            },
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Composable
fun InvestigationInterestItem(
    interest: InterestCategory,
    isSelected: Boolean,
    onClick: (InterestCategory) -> Unit
) {
    Box(
        modifier = Modifier
            .removeRippleEffect { onClick(interest) }
            .height(48.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = if (isSelected) Primary_Normal else Line_Neutral
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = interest.value,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Primary_Normal else Caption_Neutral,
            maxLines = 1,
        )
    }
}

@Preview(
    name = "InvestigationStep2Screen Preview",
    showBackground = true
)
@Composable
fun InvestigationStep2ScreenPreview() {
    DefaultWhiteTheme {
        InvestigationStep2Screen(
            onNext = {

            },
            onBack = {

            },
            viewModel = hiltViewModel()
        )
    }
}