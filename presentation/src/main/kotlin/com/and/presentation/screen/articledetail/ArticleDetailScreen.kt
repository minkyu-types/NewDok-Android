package com.and.presentation.screen.articledetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.component.image.CommonImage
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading1
import com.and.presentation.ui.Neutral2

@Composable
fun ArticleDetailScreen(
    article: DailyArticleModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background_System)
    ) {
        TopBar(
            title = article.brandName,
            onNavigationIconClick = onBack,
            actionIcon = painterResource(R.drawable.ic_line_bookmark),
            onActionButtonClick = {

            }
        )
        ArticleCard(
            article = article
        )
        ArticleBody(
            article = article
        )
    }
}

@Composable
fun ArticleCard(
    article: DailyArticleModel,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(274.dp)
    ) {
        CommonImage(
            imageUrl = article.imageUrl ?: "이미지 없음?",
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Text(
                text = article.articleTitle,
                style = Heading1,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = article.status,
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Neutral2
            )
        }
    }
}

@Composable
fun ArticleBody(
    article: DailyArticleModel,
    modifier: Modifier = Modifier
) {

}

@Preview(
    name = "ArticleDetailScreen Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun ArticleDetailScreenPreview() {
    DefaultWhiteTheme {
        ArticleCard(
            article = DailyArticleModel(
                "주간 컴퍼니타임스",
                "",
                "신입사원 시절 '최악의 실수'는?",
                1,
                "6월14일 (수) 오전 5:57",
            )

        )
//        ArticleDetailScreen(
//            onBack = {
//
//            }
//        )
    }
}