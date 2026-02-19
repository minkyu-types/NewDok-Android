package com.and.presentation.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.and.domain.model.type.IndustryCategory
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.screen.alarm.AlarmScreen
import com.and.presentation.screen.articledetail.ArticleDetailScreen
import com.and.presentation.screen.bookmark.BookmarkScreen
import com.and.presentation.screen.feed.FeedScreen
import com.and.presentation.screen.home.HomeScreen
import com.and.presentation.screen.mypage.FaqScreen
import com.and.presentation.screen.mypage.profile.IndustryEditScreen
import com.and.presentation.screen.mypage.profile.InterestEditScreen
import com.and.presentation.screen.mypage.MyPageScreen
import com.and.presentation.screen.mypage.profile.NicknameEditScreen
import com.and.presentation.screen.mypage.notification.NotificationSettingScreen
import com.and.presentation.screen.mypage.profile.ProfileEditScreen
import com.and.presentation.screen.mypage.ServiceFeedbackScreen
import com.and.presentation.screen.mypage.TermsScreen
import com.and.presentation.screen.mypage.account.AccountManageScreen
import com.and.presentation.screen.mypage.account.PasswordEditScreen
import com.and.presentation.screen.mypage.account.PhoneNumberEditScreen
import com.and.presentation.screen.mypage.account.WithdrawalStep1Screen
import com.and.presentation.screen.mypage.account.WithdrawalStep2Screen
import com.and.presentation.screen.mypage.profile.ProfileEditViewModel
import com.and.presentation.screen.newsletterdetail.NewsLetterDetailScreen
import com.and.presentation.screen.preinvestigation.InvestigationStep
import com.and.presentation.screen.search.SearchScreen
import com.and.presentation.screen.subscription.SubscriptionScreen
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Primary_Normal

@Composable
fun MainFlowScreen(
    rootNavController: NavController,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarRoutes = listOf(
        "FeedMain", "SubscriptionMain", "HomeMain", "BookmarkMain", "MyPageMain"
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "HomeMain",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("SearchMain") {
                SearchScreen(
                    onBack = { navController.popBackStack() },
                    onNewsLetterClick = {
                        navController.navigate("NewsLetterDetail")
                    },
                    onArticleClick = { article ->

                    },
                    viewModel = hiltViewModel()
                )
            }

            composable("NotificationMain") {
                AlarmScreen(
                    onBack = { navController.popBackStack() },
                    onArticleClick = { article: DailyArticleModel ->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("article", article)
                        navController.navigate("ArticleDetail")
                    },
                    onActionClick = {
                        navController.navigate("NotificationSetting")
                    }
                )
            }
            composable("NotificationSetting") {
                NotificationSettingScreen(
                    onBack = { navController.popBackStack() },
                    viewModel = hiltViewModel()
                )
            }

            composable("FeedMain") {
                FeedScreen(
                    onNewsLetterClick = { id ->
                        navController.navigate("NewsLetterDetail/${id}")
                    },
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onAlarmClick = {

                    }
                )
            }

            composable("SubscriptionMain") {
                SubscriptionScreen(
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    }
                )
            }

            composable("HomeMain") {
                HomeScreen(
                    onArticleClick = { article ->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("article", article)
                        navController.navigate("ArticleDetail")
                    },
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onAlarmClick = {

                    }
                )
            }

            composable("BookmarkMain") {
                BookmarkScreen(
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onArticleClick = { article ->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("article", article)
                        navController.navigate("ArticleDetail")
                    }
                )
            }

            composable("MyPageMain") {
                MyPageScreen(
                    onProfileEditClick = {
                        navController.navigate("ProfileEditMain")
                    },
                    onAccountManageClick = {
                        navController.navigate("AccountManage")
                    },
                    onAlarmSettingClick = {
                        navController.navigate("NotificationSetting")
                    },
                    onFaqClick = {
                        navController.navigate("Faq")
                    },
                    onFeedbackClick = {
                        navController.navigate("ServiceFeedback")
                    },
                    onTermClick = {
                        navController.navigate("Term")
                    },
                    onVersionClick = {
                        // 버전 화면 누락
                    }
                )
            }

            composable("AccountManage") {
                AccountManageScreen(
                    onBack = { navController.popBackStack() },
                    onPhoneNumChange = {
                        navController.navigate("PhoneNumberEdit")
                    },
                    onPasswordChange = {
                        navController.navigate("PasswordEdit")
                    },
                    onLogout = onLogout,
                    onTryWithdrawal = {
                        navController.navigate("WithdrawalStep1")
                    }
                )
            }

            composable("PhoneNumberEdit") {
                PhoneNumberEditScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = hiltViewModel()
                )
            }

            composable("PasswordEdit") {
                PasswordEditScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }

            composable("WithdrawalStep1") {
                WithdrawalStep1Screen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNext = {
                        navController.navigate("WithdrawalStep2")
                    },
                    viewModel = hiltViewModel()
                )
            }

            composable("WithdrawalStep2") {
                WithdrawalStep2Screen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onWithdrawal = {
                        rootNavController.navigate(ScreenFlow.LOGIN.route) {
                            popUpTo(rootNavController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    viewModel = hiltViewModel()
                )
            }

            composable("Faq") {
                FaqScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("ServiceFeedback") {
                ServiceFeedbackScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("Term") {
                TermsScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("ProfileEditMain") {
                ProfileEditScreen(
                    onBack = { navController.popBackStack() },
                    onNickNameClick = {
                        navController.navigate("NicknameEdit")
                    },
                    onIndustryClick = { industry ->
                        navController.navigate("IndustryEdit/${industry.name}")
                    },
                    onInterestClick = {
                        navController.navigate("InterestEdit")
                    },
                    viewModel = hiltViewModel()
                )
            }

            navigation(
                route = "profile_edit_graph",
                startDestination = InvestigationStep.STEP_1_INDUSTRY.route
            ) {
                composable("NicknameEdit") { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("profile_edit_graph")
                    }
                    val viewModel: ProfileEditViewModel = hiltViewModel(parentEntry)

                    NicknameEditScreen(
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }

                composable(
                    route = "IndustryEdit/{industry}",
                    arguments = listOf(
                        navArgument("industry") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("profile_edit_graph")
                    }
                    val enumName = backStackEntry.arguments?.getString("industry")
                        ?: IndustryCategory.DEFAULT.name
                    val industry = runCatching { IndustryCategory.valueOf(enumName) }
                        .getOrDefault(IndustryCategory.DEFAULT)
                    val viewModel: ProfileEditViewModel = hiltViewModel(parentEntry)

                    IndustryEditScreen(
                        industry = industry,
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }

                composable("InterestEdit") { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("profile_edit_graph")
                    }
                    val viewModel: ProfileEditViewModel = hiltViewModel(parentEntry)

                    InterestEditScreen(
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }
            }

            composable("ArticleDetail") { backStackEntry ->
                val article = remember(backStackEntry) {
                    navController
                        .previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<DailyArticleModel>("article")
                }

                if (article != null) {
                    ArticleDetailScreen(
                        article = article,
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    LaunchedEffect(Unit) {
                        navController.popBackStack()
                    }
                }
            }

            composable(
                route = "NewsLetterDetail/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        defaultValue = 0
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                val newsLetterId = backStackEntry.arguments?.getInt("id")
                    ?: throw IllegalArgumentException("뉴스레터 ID가 존재하지 않습니다")
                NewsLetterDetailScreen(
                    id = newsLetterId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationItem.Feed,
        BottomNavigationItem.Subscription,
        BottomNavigationItem.Home,
        BottomNavigationItem.Bookmark,
        BottomNavigationItem.MyPage,
    )

    Column {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Line_Disabled)
        )
        NavigationBar(
            containerColor = Color.White
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = if (currentRoute == item.route) painterResource(item.selectedIcon)
                            else painterResource(item.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = item.label)
                    },
                    selected = (currentRoute == item.route),
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Primary_Normal,
                        selectedTextColor = Primary_Normal,
                        unselectedIconColor = Caption_Alternative,
                        unselectedTextColor = Caption_Alternative,
                        indicatorColor = Color.Transparent
                    ),
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}