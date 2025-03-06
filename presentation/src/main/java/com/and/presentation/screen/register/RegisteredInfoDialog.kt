package com.and.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import com.and.presentation.R
import com.and.presentation.component.button.ButtonSize
import com.and.presentation.component.button.OutlinedPrimaryButton
import com.and.presentation.component.button.SolidPrimaryButton
import com.and.presentation.model.RegisteredAccountModel
import com.and.presentation.screen.login.LoginTheme
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.util.toLocalDateWithDot
import com.and.presentation.util.toMaskedString
import java.time.LocalDate

/**
 * @author Loki
 * 회원 가입 시 해당 전화번호로 가입된 계정이 있는 경우 표시되는 다이얼로그
 * 1개의 전화번호로 최대 3개의 계정을 생성할 수 있음
 */
@Composable
fun RegisterFailDialog(
    onDismiss: () -> Unit,
    registeredAccountModels: List<RegisteredAccountModel>,
    onContinue: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isUserLessThanThree = registeredAccountModels.size < 3

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_warning),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(R.string.auth_request_fail_title),
                        style = Heading2,
                        fontWeight = FontWeight.Bold,
                        color = Caption_Heavy,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(R.string.auth_request_fail_body),
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Caption_Neutral,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(Background_System)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(registeredAccountModels) { account ->
                            RegisteredAccountItem(account)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    OutlinedPrimaryButton(
                        buttonText = if (isUserLessThanThree) stringResource(R.string.auth_request_fail_button_left)
                        else stringResource(R.string.find_id_password),
                        buttonSize = ButtonSize.LARGE,
                        modifier = Modifier.weight(1f),
                        onClick = {

                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SolidPrimaryButton(
                        buttonText = stringResource(R.string.login),
                        buttonSize = ButtonSize.LARGE,
                        modifier = Modifier.weight(1f),
                        onClick = onLogin
                    )
                }
            }
        }
    }
}

@Composable
private fun RegisteredAccountItem(
    account: RegisteredAccountModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = account.id.toMaskedString(),
            style = Body2Normal,
            fontWeight = FontWeight.Medium,
            color = Caption_Heavy,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = account.registeredDate.toLocalDateWithDot(),
            style = Label1,
            fontWeight = FontWeight.Medium,
            color = Caption_Neutral
        )
    }
}

@Preview(
    name = "RegisterFailDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun RegisterFailDialogPreview() {
    LoginTheme {
        RegisterFailDialog(
            onDismiss = {

            },
            registeredAccountModels = listOf(
                RegisteredAccountModel("lion9638", LocalDate.now()),
                RegisteredAccountModel("cat1234", LocalDate.now().minusMonths(2)),
                RegisteredAccountModel("dog6789", LocalDate.now().minusMonths(12)),
            ),
            onContinue = {

            },
            onLogin = {

            }
        )
    }
}