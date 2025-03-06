package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.Gender
import com.and.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedSecondaryButton
import com.and.presentation.model.UserModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MyPageProfileArea(
            user = UserModel(
                1,
                "",
                "",
                "lion9638@naver.com",
                "닉네임최대열두글자",
                "",
                Gender.MALE,
                "",
                1,
                emptyList(),
            )
        )
        MyPageServiceArea()
        Spacer(modifier = Modifier.height(12.dp))
        MyPageCustomerServiceArea()
    }
}

@Composable
fun MyPageProfileArea(
    user: UserModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 24.dp)
    ) {
        Text(
            text = user.nickname.take(12),
            style = Body1Normal,
            fontWeight = FontWeight.Bold,
            color = Caption_Heavy
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.my_page_profile_email),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.ic_line_question_mark),
                tint = Caption_Neutral,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        // 구독 이메일 팝업 표시
                    }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .removeRippleEffect {
                    // 구독 이메일 클립보드에 복사
                }
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_line_copy),
                    tint = Primary_Normal,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = user.subscribeEmail,
                    style = Body2Normal,
                    fontWeight = FontWeight.Normal,
                    color = Caption_Heavy
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedSecondaryButton(
            buttonText = stringResource(R.string.my_page_profile_edit),
            buttonSize = ButtonSize.LARGE,
            fontWeight = FontWeight.Bold,
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun MyPageServiceArea(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
           text = stringResource(R.string.my_page_service_title),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Assistive,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        MyPageItem(
            text = stringResource(R.string.my_page_service_item_1),
            onClick = {

            }
        )
        MyPageItem(
            text = stringResource(R.string.my_page_service_item_2),
            onClick = {

            }
        )
    }
}

@Composable
fun MyPageCustomerServiceArea(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.my_page_customer_service_title),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Assistive,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        MyPageItem(
            text = stringResource(R.string.my_page_customer_service_item1),
            onClick = {

            }
        )
        MyPageItem(
            text = stringResource(R.string.my_page_customer_service_item2),
            onClick = {

            }
        )
        MyPageItem(
            text = stringResource(R.string.my_page_customer_service_item3),
            onClick = {

            }
        )
        MyPageItem(
            text = stringResource(R.string.my_page_customer_service_item4),
            onClick = {

            }
        )
    }
}

@Composable
private fun MyPageItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.ic_line_right),
            contentDescription = null,
            tint = Caption_Neutral
        )
    }
}

@Preview(
    name = "MyPageScreen Preview",
    showBackground = true
)
@Composable
fun MyPageScreenPreview() {
    DefaultWhiteTheme {
        MyPageScreen(

        )
    }
}