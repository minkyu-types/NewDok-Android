package com.and.presentation.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.and.domain.model.type.Gender
import com.and.newdok.presentation.R
import com.and.presentation.component.WebViewScreen
import com.and.presentation.component.button.ConditionalNextButton
import com.and.presentation.component.dialog.BottomSheetDialog
import com.and.presentation.component.textfield.HintErrorTextField
import com.and.presentation.component.topbar.ProgressTopBar
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Caption_Neutral
import com.and.presentation.ui.Heading2
import com.and.presentation.ui.Label1
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.util.NICKNAME_MAX_LENGTH
import com.and.presentation.util.UiState
import com.and.presentation.util.nicknameValidation
import kotlinx.coroutines.launch
import java.time.LocalDate

enum class KakaoRegisterStep(val route: String, val step: Int) {
    STEP_1_USER_INFO("kakao_register_step1_user_info", 1),
    STEP_2_TERMS("kakao_register_step2_terms", 2),
    STEP_3_COMPLETE("kakao_register_step3_complete/{email}", 3),
    STEP_4_ADDITIONAL_INFO("kakao_register_step4_additional_info", 4);

    companion object {
        fun getStepByRoute(route: String): Int {
            return entries.firstOrNull { it.route == route }?.step
                ?: throw IllegalArgumentException("잘못된 route 값입니다: $route")
        }
    }
}

/**
 * 카카오 신규회원 가입 플로우
 *
 * 카카오 로그인 결과가 NeedSignup(signupToken + profile)일 때 진입한다.
 * 닉네임/출생연도/성별 입력 → 약관 동의(가입 요청) → 가입 완료 안내 순서로 진행하며,
 * 완료 화면은 이메일 가입 플로우의 화면(RegisterStep6/7Screen)을 재사용한다.
 */
@Composable
fun KakaoRegisterFlowScreen(
    onFlowFinished: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: KakaoRegisterViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String =
        currentBackStackEntry?.destination?.route ?: KakaoRegisterStep.STEP_1_USER_INFO.route
    val currentProgress: Int = KakaoRegisterStep.getStepByRoute(currentRoute)

    Scaffold(
        topBar = {
            ProgressTopBar(
                title = stringResource(id = R.string.register),
                currentProgress = currentProgress,
                maxProgress = 4,
                onNavigationIconClick = {
                    val canPop = navController.popBackStack()
                    if (!canPop) {
                        onBack()
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = KakaoRegisterStep.STEP_1_USER_INFO.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(KakaoRegisterStep.STEP_1_USER_INFO.route) {
                KakaoRegisterStep1Screen(
                    onNext = { navController.navigate(KakaoRegisterStep.STEP_2_TERMS.route) },
                    viewModel = viewModel
                )
            }
            composable(KakaoRegisterStep.STEP_2_TERMS.route) {
                KakaoRegisterStep2Screen(
                    onNext = { email ->
                        navController.navigate("kakao_register_step3_complete/$email") {
                            // 가입이 완료된 뒤에는 입력/약관 화면으로 되돌아갈 수 없어야 한다
                            popUpTo(KakaoRegisterStep.STEP_1_USER_INFO.route) { inclusive = true }
                        }
                    },
                    viewModel = viewModel
                )
            }
            composable(
                route = KakaoRegisterStep.STEP_3_COMPLETE.route,
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""

                RegisterStep6Screen(
                    onNext = { navController.navigate(KakaoRegisterStep.STEP_4_ADDITIONAL_INFO.route) },
                    subscriptionEmail = email
                )
            }
            composable(KakaoRegisterStep.STEP_4_ADDITIONAL_INFO.route) {
                RegisterStep7Screen(
                    onNext = { onFlowFinished() },
                )
            }
        }
    }
}

/**
 * 카카오 가입 1단계: 닉네임/출생연도/성별 입력 (카카오 프로필 닉네임을 초기값으로 사용)
 */
@Composable
fun KakaoRegisterStep1Screen(
    onNext: () -> Unit,
    viewModel: KakaoRegisterViewModel,
    modifier: Modifier = Modifier
) {
    var userNickname by rememberSaveable { mutableStateOf("") }
    val isNicknameValid = userNickname.nicknameValidation()
    val years = (1970..LocalDate.now().year.minus(12)).map { it.toString() }
    var userGender by rememberSaveable { mutableStateOf<Gender?>(null) }
    var selectedYear: String? by rememberSaveable { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_nickname_title),
                style = Heading2,
                fontWeight = FontWeight.Bold,
                color = Caption_Heavy,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.nickname),
                style = Body2Normal,
                fontWeight = FontWeight.Medium,
                color = Caption_Neutral,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            HintErrorTextField(
                maxLength = NICKNAME_MAX_LENGTH,
                value = userNickname,
                onValueChange = { userNickname = it },
                valueHint = stringResource(id = R.string.register_nickname_placeholder),
                isError = userNickname.isNotBlank() && !isNicknameValid,
            )
            if (userNickname.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                if (!isNicknameValid) {
                    Text(
                        text = stringResource(R.string.register_nickname_placeholder),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = stringResource(R.string.register_nickname_valid),
                        style = Label1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Primary_Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            RegisterBirthYear(
                years = years,
                selectedItem = selectedYear,
                onSelect = { year ->
                    selectedYear = year
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            RegisterGenderRadioGroup(
                userGender = userGender,
                onClick = { userGender = it }
            )
        }

        ConditionalNextButton(
            enabled = isNicknameValid && (selectedYear != null) && (userGender != null),
            onClick = {
                viewModel.setUserNicknameBirthGender(
                    nickname = userNickname,
                    birth = selectedYear,
                    gender = userGender
                )
                onNext()
            },
            modifier = Modifier.padding(24.dp)
        )
    }
}

/**
 * 카카오 가입 2단계: 약관 동의 후 가입 요청
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KakaoRegisterStep2Screen(
    onNext: (String) -> Unit,
    viewModel: KakaoRegisterViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var bottomSheetUrl by remember { mutableStateOf<String?>(null) }

    var isTerm1Checked by remember { mutableStateOf(false) }
    var isTerm2Checked by remember { mutableStateOf(false) }
    var isTerm3Checked by remember { mutableStateOf(false) }
    var isTerm4Checked by remember { mutableStateOf(false) }
    val isAllTermsChecked = isTerm1Checked && isTerm2Checked
            && isTerm3Checked && isTerm4Checked

    val signUpState by viewModel.signUpState

    LaunchedEffect(signUpState) {
        when (val state = signUpState) {
            is UiState.Success -> {
                onNext(state.data.subscribeEmail)
            }
            is UiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                viewModel.consumeSignUpError()
            }
            else -> {}
        }
    }

    if (showBottomSheet) {
        BottomSheetDialog(
            title = stringResource(R.string.terms_common),
            sheetState = sheetState,
            onDismiss = {
                showBottomSheet = false
            },
            onHideRequested = {
                coroutineScope.launch {
                    sheetState.hide()
                }
                showBottomSheet = false
            },
            content = {
                bottomSheetUrl?.let {
                    WebViewScreen(it)
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.register_terms_title),
                style = Heading2,
                color = Caption_Heavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_1),
                initialChecked = isTerm1Checked,
                onCheckChange = { isTerm1Checked = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_2),
                initialChecked = isTerm2Checked,
                onCheckChange = {
                    if (it) {
                        bottomSheetUrl = context.getString(R.string.term_service_url)
                        showBottomSheet = true
                    }
                    isTerm2Checked = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_3),
                initialChecked = isTerm3Checked,
                onCheckChange = {
                    if (it) {
                        bottomSheetUrl = context.getString(R.string.term_personal_url)
                        showBottomSheet = true
                    }
                    isTerm3Checked = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            RegisterTermAgreeButton(
                title = stringResource(R.string.register_terms_term_4),
                initialChecked = isTerm4Checked,
                onCheckChange = { isTerm4Checked = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            )
            RegisterTermAgreeAllButton(
                initialChecked = isAllTermsChecked,
                onCheckChange = { newState ->
                    isTerm1Checked = newState
                    isTerm2Checked = newState
                    isTerm3Checked = newState
                    isTerm4Checked = newState
                }
            )
        }

        ConditionalNextButton(
            buttonText = stringResource(R.string.register_terms_complete),
            enabled = isTerm1Checked && isTerm2Checked && isTerm3Checked
                    && signUpState !is UiState.Loading,
            onClick = {
                viewModel.signUp(marketingAgreed = isTerm4Checked)
            },
            modifier = Modifier.padding(24.dp)
        )
    }
}
