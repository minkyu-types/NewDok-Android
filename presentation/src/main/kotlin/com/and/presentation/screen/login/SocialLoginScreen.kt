package com.and.presentation.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.newdok.presentation.R
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.DefaultWhiteTheme
import com.and.presentation.ui.Label1
import com.and.presentation.util.removeRippleEffect

private val KakaoYellow = Color(0xFFFEE500)
private val KakaoTextColor = Color(0xFF161616)
private val AnimTriggerRed = Color(0xFFFB4F4F)

@Composable
fun SocialLoginScreen(
    onKakaoLoginClick: () -> Unit,
    onGuestModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(226.dp)
                .padding(bottom = 56.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            SocialLoginLogo()
        }

        // Contents Area - fills remaining space, buttons at bottom
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 56.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // SNS Login Buttons with AnimTrigger badge
            SocialLoginButtons(
                onKakaoLoginClick = onKakaoLoginClick
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Guest mode text
            Text(
                text = stringResource(id = R.string.login_without_register),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.removeRippleEffect { onGuestModeClick() }
            )
        }
    }
}

@Composable
private fun SocialLoginLogo(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = stringResource(id = R.string.logo_image),
            modifier = Modifier.height(48.dp)
        )
    }
}

@Composable
private fun SocialLoginButtons(
    onKakaoLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animVisibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Kakao Login Button
            SocialLoginButton(
                text = stringResource(id = R.string.social_login_kakao),
                iconRes = R.drawable.ic_kakao_logo,
                backgroundColor = KakaoYellow,
                textColor = KakaoTextColor,
                iconTint = null,
                onClick = onKakaoLoginClick
            )
        }

        // "3초만에 시작하기" AnimTrigger badge
        AnimatedVisibility(
            visibleState = animVisibleState,
            enter = fadeIn(animationSpec = tween(600)) +
                    slideInVertically(
                        animationSpec = tween(600),
                        initialOffsetY = { it / 2 }
                    ),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-28).dp)
        ) {
            AnimTriggerBadge()
        }
    }
}

@Composable
private fun SocialLoginButton(
    text: String,
    iconRes: Int,
    backgroundColor: Color,
    textColor: Color,
    iconTint: Color?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .removeRippleEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconTint != null) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = text,
                style = Body1Normal,
                fontWeight = FontWeight.Medium,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AnimTriggerBadge(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(AnimTriggerRed)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.social_login_anim_trigger),
                style = Label1,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
        // Triangle arrow pointing down
        TriangleDown(
            color = AnimTriggerRed,
            modifier = Modifier.size(width = 8.dp, height = 4.dp)
        )
    }
}

@Composable
private fun TriangleDown(
    color: Color,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val path = androidx.compose.ui.graphics.Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width / 2f, size.height)
            close()
        }
        drawPath(path, color)
    }
}

@Preview(
    name = "SocialLoginScreen Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SocialLoginScreenPreview() {
    DefaultWhiteTheme {
        SocialLoginScreen(
            onKakaoLoginClick = { },
            onGuestModeClick = { }
        )
    }
}
