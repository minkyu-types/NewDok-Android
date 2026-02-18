package com.and.presentation.screen.preinvestigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.domain.model.Industry
import com.and.newdok.presentation.R
import com.and.presentation.component.IndustryDropDown
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.ui.Tint10

@Composable
fun InvestigationStep1Screen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InvestigationViewModel
) {
    var selectedIndustry: Industry? by rememberSaveable { mutableStateOf(null) }
    val industries by viewModel.industries.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getIndustries()
    }

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
                text = stringResource(R.string.pre_investigation_industry_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.pre_investigation_industry_body),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            IndustryDropDown(
                industries = industries,
                onSelect = {
                    selectedIndustry = it
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        ConditionalNextButton(
            enabled = (selectedIndustry != null),
            onClick = {
                selectedIndustry?.let { industry ->
                    viewModel.updateIndustry(industry)
                    onNext()
                }
            },
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Composable
fun IndustryDropDownItem(
    selectedItem: Industry?,
    industry: Industry,
    onSelect: (Industry) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            Text(
                text = industry.name,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = if (selectedItem == industry)
                    Primary_Normal
                else
                    Caption_Strong,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        },
        onClick = {
            onSelect(industry)
        },
        modifier = Modifier
            .height(48.dp)
            .background(
                if (selectedItem == industry) Tint10
                else Color.White
            )
    )
}

@Preview(
    name = "InvestigationStep1Screen Preview",
    showBackground = true
)
@Composable
fun InvestigationStep1ScreenPreview() {
    DefaultWhiteTheme {
        InvestigationStep1Screen(
            onNext = {

            },
            onBack = {

            },
            viewModel = hiltViewModel()
        )
    }
}