package com.and.presentation.screen.articledetail

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.and.domain.model.ArticleDetail
import com.and.newdok.presentation.R
import com.and.presentation.component.image.CommonImage
import com.and.presentation.component.topbar.TopBar
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Heading1
import com.and.presentation.ui.Neutral2
import com.and.presentation.util.UiState

@Composable
fun ArticleDetailScreen(
    articleId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.articleDetailUiState

    LaunchedEffect(articleId) {
        viewModel.getArticleDetail(articleId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background_System)
    ) {
        when (uiState) {
            is UiState.Success -> {
                val data = (uiState as UiState.Success<ArticleDetail>).data
                TopBar(
                    title = data.brandName,
                    onNavigationIconClick = onBack,
                    actionIcon = painterResource(R.drawable.ic_line_bookmark),
                    onActionButtonClick = {

                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ArticleCard(
                        title = data.articleTitle,
                        imageUrl = data.brandImageUrl
                    )
                    ArticleBody(
                        articleHTML = data.articleHTML,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            is UiState.Loading -> {
                TopBar(
                    title = "",
                    onNavigationIconClick = onBack
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                TopBar(
                    title = "",
                    onNavigationIconClick = onBack
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_line_question_mark),
                            contentDescription = null,
                            tint = Neutral2,
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = errorMessage ?: "아티클을 불러올 수 없습니다.",
                            style = Body2Normal,
                            color = Neutral2,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            else -> {
                TopBar(
                    title = "",
                    onNavigationIconClick = onBack
                )
            }
        }
    }
}

@Composable
fun ArticleCard(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .fillMaxWidth()
            .height(274.dp)
    ) {
        CommonImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Text(
                text = title,
                style = Heading1,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
@Composable
fun ArticleBody(
    articleHTML: String,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    var webViewHeight by remember { mutableIntStateOf(0) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    useWideViewPort = false
                    loadWithOverviewMode = false
                    cacheMode = WebSettings.LOAD_DEFAULT
                    mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                }
                isVerticalScrollBarEnabled = false
                isHorizontalScrollBarEnabled = false

                fun measureAndUpdateHeight() {
                    evaluateJavascript(
                        """
                        (function() {
                            var body = document.body;
                            var html = document.documentElement;
                            return Math.max(
                                body.scrollHeight,
                                body.offsetHeight,
                                html.clientHeight,
                                html.scrollHeight,
                                html.offsetHeight
                            );
                        })();
                        """.trimIndent()
                    ) { result ->
                        val calculatedHeight = result.replace("\"", "").toIntOrNull() ?: 0
                        if (calculatedHeight > 0) {
                            if (calculatedHeight > webViewHeight) {
                                Log.d(
                                    "ArticleDetail",
                                    "Updating height from $webViewHeight to $calculatedHeight (calculated: $calculatedHeight)"
                                )
                                webViewHeight = calculatedHeight
                            }
                        }
                    }
                }

                addJavascriptInterface(object {
                    @android.webkit.JavascriptInterface
                    fun onHeightChanged(height: Int) {
                        post {
                            if (height > 0 && height > webViewHeight) {
                                webViewHeight = height
                            }
                        }
                    }
                }, "AndroidInterface")

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        // ResizeObserver가 자동 감지하므로 별도 호출 불필요
                        // 안전장치로 한 번만
                        view?.postDelayed({ measureAndUpdateHeight() }, 500)
                    }
                }

                val htmlContent = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                        <style>
                            html, body {
                                margin: 0;
                                padding: 0;
                                width: 100%;
                                overflow-x: hidden;
                                overflow-y: visible;
                            }
                            body {
                                padding: 16px;
                                padding-bottom: 24px;
                                font-family: -apple-system, sans-serif;
                                word-break: break-word;
                                overflow-wrap: break-word;
                                box-sizing: border-box;
                                min-height: 100%;
                            }
                            img {
                                max-width: 100%;
                                height: auto;
                                display: block;
                            }
                            * {
                                max-width: 100%;
                                box-sizing: border-box;
                            }
                        </style>
                        <script>
                        // ResizeObserver로 body 크기 변화 자동 감지
                        var lastHeight = 0;
                        var resizeObserver = new ResizeObserver(function(entries) {
                            var body = document.body;
                            var html = document.documentElement;
                            var height = Math.max(
                                body.scrollHeight,
                                body.offsetHeight,
                                html.clientHeight,
                                html.scrollHeight,
                                html.offsetHeight
                            );
                            if (height !== lastHeight && height > 0) {
                                lastHeight = height;
                                if (window.AndroidInterface) {
                                    window.AndroidInterface.onHeightChanged(height);
                                }
                            }
                        });
                    
                        // DOM 로드 후 관찰 시작
                        document.addEventListener('DOMContentLoaded', function() {
                            resizeObserver.observe(document.body);
                            resizeObserver.observe(document.documentElement);
                        });
                    
                        // 이미지 로드 시에도 재측정 (ResizeObserver가 자동 감지하지만 보험용)
                        window.addEventListener('load', function() {
                            var images = document.getElementsByTagName('img');
                            for (var i = 0; i < images.length; i++) {
                                if (!images[i].complete) {
                                    images[i].addEventListener('load', function() {
                                        // ResizeObserver가 자동으로 처리
                                    });
                                }
                            }
                        });
                    </script>
                    </head>
                    <body>
                        $articleHTML
                    </body>
                    </html>
                """.trimIndent()
                loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (webViewHeight > 0) {
                    Modifier.height(webViewHeight.dp)  // ← dp 직접 사용
                } else {
                    Modifier.defaultMinSize(minHeight = 1000.dp)  // 30000.dp 대신 적절한 초기값
                }
            )
    )
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
            title = "신입사원 시절 '최악의 실수'는?",
            imageUrl = ""
        )
    }
}
