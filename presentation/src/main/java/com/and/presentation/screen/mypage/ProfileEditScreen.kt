package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.Gender
import com.and.domain.model.type.InterestCategory
import com.and.presentation.R
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.UserModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Line_Alternative
import com.and.presentation.util.removeRippleEffect

@Composable
fun ProfileEditScreen(
    onBack: () -> Unit,
    onNickNameClick: () -> Unit,
    onIndustryClick: () -> Unit,
    onInterestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = UserModel(
        0,
        "",
        "",
        "",
        "모아",
        "",
        Gender.MALE,
        "",
        1,
        listOf(
            InterestCategory.INTEREST_TRAVEL
        ),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.my_page_profile_edit),
            onNavigationIconClick = onBack,
        )
        Text(
            text = stringResource(R.string.profile_edit_head),
            style = Heading2,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 32.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            ProfileEditBox(
                title = stringResource(R.string.nickname),
                value = user.nickname,
                onClick = onNickNameClick
            )
            ProfileEditBox(
                title = stringResource(R.string.industry_category),
                value = null,
                placeHolder = stringResource(R.string.profile_edit_industry_placeholder),
                onClick = onIndustryClick
            )
            ProfileEditBox(
                title = stringResource(R.string.interest_category),
                value = null,
                placeHolder = stringResource(R.string.profile_edit_interest_placeholder),
                onClick = onInterestClick
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileEditBox(
    title: String,
    value: String?,
    placeHolder: String? = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mainColor = if (value == null) Caption_Assistive else Caption_Strong

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = title,
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral,
            modifier = Modifier.padding(start = 4.dp)
        )
        Box(
            modifier = Modifier
                .removeRippleEffect { onClick() }
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = Line_Alternative,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(vertical = 12.dp, horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value ?: placeHolder ?: "",
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = mainColor,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_line_edit),
                    contentDescription = null,
                    tint = mainColor
                )
            }
        }
    }
}

@Preview(
    name = "ProfileEditScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun ProfileEditScreenPreview() {
    DefaultWhiteTheme {
        ProfileEditScreen(
            onBack = {

            },
            onNickNameClick = {

            },
            onIndustryClick = {

            },
            onInterestClick = {

            }
        )
    }
}