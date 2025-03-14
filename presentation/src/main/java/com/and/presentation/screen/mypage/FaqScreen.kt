package com.and.presentation.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.and.presentation.R
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.FaqModel
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Line_Neutral
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.removeRippleEffect

@Composable
fun FaqScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val faqList = listOf(
        FaqModel(
            " 뉴스레터",
            "구독 이메일이 뭔가요?",
            "구독이메일은 구독이메일은 구독이메일은 구독이메일은 구독이메일은 ",
        ),
        FaqModel(
            " 큐레이션",
            "관련이 없는 뉴스레터만 추천돼요. 어떻게 하나요? 어떻게 할까요? 엏떢계?",
            "구독이메일은 구독이메일은 구독이메일은 구독이메일은 구독이메일은 ",
        ),
        FaqModel(
            " 뉴스레터",
            "구독 이메일이 뭔가요?",
            "구독이메일은 구독이메일은 구독이메일은 구독이메일은 구독이메일은 ",
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(
            title = stringResource(R.string.faq_title),
            onNavigationIconClick = onBack,
        )
        FaqList(
            faqList = faqList,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
        )
    }
}

@Composable
fun FaqList(
    faqList: List<FaqModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(faqList) { faq ->
            FaqItem(faq)
        }
    }
}

@Composable
private fun FaqItem(
    faqModel: FaqModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .removeRippleEffect {
                expanded = !expanded
            }
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (expanded) Primary_Normal else Line_Neutral,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = faqModel.category,
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Primary_Normal,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = faqModel.title,
                    style = Body1Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Heavy,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = if (expanded) painterResource(R.drawable.ic_line_up)
                    else painterResource(R.drawable.ic_line_down),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = faqModel.body,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Caption_Neutral,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    name = "FaqScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun FaqScreenPreview() {
    DefaultWhiteTheme {
        FaqScreen(
            onBack = {
                
            }
        )
    }
}