package com.and.presentation.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.newdok.presentation.R
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Gray700
import com.and.presentation.ui.Gray800
import com.and.presentation.ui.Line_Neutral

@Composable
fun ArticleSimpleItem(
    newsLetter: NewsLetterModel,
    onClick: (NewsLetterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick(newsLetter) }
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Line_Neutral,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White
            ),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White
                )
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Line_Neutral,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = newsLetter.name,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Gray700,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = newsLetter.repeatTerm,
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Gray800,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    name = "ArticleSimpleItem Preview",
    showBackground = true
)
@Composable
fun ArticleSimpleItemPreview() {
    DefaultWhiteTheme {
    }
}