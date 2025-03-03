package com.and.presentation.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Assistive
import com.and.presentation.ui.Caption_Strong
import com.and.presentation.ui.DefaultWhiteTheme

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MyPageProfileArea()
        Spacer(modifier = Modifier.height(12.dp))
        MyPageServiceArea()
        Spacer(modifier = Modifier.height(12.dp))
        MyPageCustomerServiceArea()
    }
}

@Composable
fun MyPageProfileArea(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 24.dp)
    ) {

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
        Text(
            text = stringResource(R.string.my_page_service_item_1),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Text(
            text = stringResource(R.string.my_page_service_item_2),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
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
        Text(
            text = stringResource(R.string.my_page_customer_service_item1),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Text(
            text = stringResource(R.string.my_page_customer_service_item2),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Text(
            text = stringResource(R.string.my_page_customer_service_item3),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Text(
            text = stringResource(R.string.my_page_customer_service_item4),
            style = Body1Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Strong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
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