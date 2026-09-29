package com.and.presentation.activity

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
import com.and.presentation.screen.mypage.account.WithdrawalStep1Screen
import com.and.presentation.screen.mypage.account.WithdrawalStep2Screen
import com.and.presentation.screen.mypage.profile.ProfileEditViewModel
import com.and.presentation.screen.newsletterdetail.NewsLetterDetailScreen
import com.and.presentation.screen.preinvestigation.InvestigationStep
import com.and.presentation.screen.preinvestigation.InvestigationStep1Screen
import com.and.presentation.screen.preinvestigation.InvestigationStep2Screen
import com.and.presentation.screen.preinvestigation.InvestigationViewModel
import com.and.presentation.screen.search.SearchScreen
import com.and.presentation.screen.subscription.SubscriptionScreen
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Primary_Normal

@Composable
fun MainFlowScreen(
    rootNavController: NavController,
    onLogout: () -> Unit,
    onNavigateToLogin: () -> Unit,
    isGuestMode: Boolean,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarRoutes = listOf(
        "FeedMain", "SubscriptionMain", "HomeMain", "BookmarkMain", "MyPageMain"
    )

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    isGuestMode = isGuestMode,
                    onNavigateToLogin = onNavigateToLogin
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "HomeMain",
            modifier = Modifier.padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 0.dp
                )
            )
        ) {
            composable("SearchMain") {
                SearchScreen(
                    onBack = { navController.popBackStack() },
                    onNewsLetterClick = { newsLetter ->
                        navController.navigate("NewsLetterDetail/${newsLetter.id}")
                    },
                    onArticleClick = { articleId ->
                        navController.navigate("ArticleDetail/$articleId")
                    },
                    viewModel = hiltViewModel()
                )
            }

            composable("NotificationMain") {
                AlarmScreen(
                    onBack = { navController.popBackStack() },
                    onArticleClick = { articleId ->
                        navController.navigate("ArticleDetail/$articleId")
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

            composable("FeedMain") { backStackEntry ->
                val industrySelected = backStackEntry
                    .savedStateHandle
                    .get<Boolean>("industrySelected") == true

                FeedScreen(
                    onNewsLetterClick = { id ->
                        navController.navigate("NewsLetterDetail/${id}")
                    },
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onAlarmClick = {

                    },
                    onNavigateToIndustrySelection = {
                        navController.navigate("industry_interest_flow")
                    },
                    onNavigateToInterestSelection = {
                        navController.navigate("InterestSelectionOnly")
                    },
                    shouldReloadRecommend = industrySelected,
                    onReloadConsumed = {
                        backStackEntry.savedStateHandle.remove<Boolean>("industrySelected")
                    },
                    isGuestMode = isGuestMode
                )
            }

            navigation(
                route = "industry_interest_flow",
                startDestination = "IndustrySelection"
            ) {
                composable("IndustrySelection") { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("industry_interest_flow")
                    }
                    val viewModel: InvestigationViewModel = hiltViewModel(parentEntry)
                    InvestigationStep1Screen(
                        onNext = { navController.navigate("InterestSelection") },
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }
                composable("InterestSelection") { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("industry_interest_flow")
                    }
                    val viewModel: InvestigationViewModel = hiltViewModel(parentEntry)
                    InvestigationStep2Screen(
                        onNext = {
                            navController.getBackStackEntry("FeedMain")
                                .savedStateHandle["industrySelected"] = true
                            navController.popBackStack("FeedMain", inclusive = false)
                        },
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }
            }

            composable("InterestSelectionOnly") {
                val viewModel: InvestigationViewModel = hiltViewModel()
                InvestigationStep2Screen(
                    onNext = {
                        navController.getBackStackEntry("FeedMain")
                            .savedStateHandle["industrySelected"] = true
                        navController.popBackStack("FeedMain", inclusive = false)
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }

            composable("SubscriptionMain") {
                SubscriptionScreen(
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    isGuestMode = isGuestMode
                )
            }

            composable("HomeMain") {
                HomeScreen(
                    onArticleClick = { article ->
                        navController.navigate("ArticleDetail/${article.articleId}")
                    },
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onAlarmClick = {

                    },
                    onViewNewsLettersClick = {
                        navController.navigateToBottomTab("FeedMain")
                    },
                    onRecommendClick = {
                        navController.navigateToBottomTab("FeedMain")
                    },
                    onSignUpClick = {
                        rootNavController.navigate(ScreenFlow.SOCIAL_LOGIN.route)
                    },
                    onLoginClick = onNavigateToLogin
                )
            }

            composable("BookmarkMain") {
                BookmarkScreen(
                    onSearchClick = {
                        navController.navigate("SearchMain")
                    },
                    onArticleClick = { articleId ->
                        navController.navigate("ArticleDetail/$articleId")
                    },
                    isGuestMode = isGuestMode
                )
            }

            composable("MyPageMain") {
                if (isGuestMode) {
                    LaunchedEffect(Unit) {
                        onNavigateToLogin()
                    }
                } else {
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
            }

            composable("AccountManage") {
                AccountManageScreen(
                    onBack = { navController.popBackStack() },
                    onLogout = onLogout,
                    onTryWithdrawal = {
                        navController.navigate("WithdrawalStep1")
                    }
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

            composable(
                route = "ArticleDetail/{articleId}",
                arguments = listOf(
                    navArgument("articleId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val articleId = backStackEntry.arguments?.getInt("articleId")
                    ?: throw IllegalArgumentException("아티클 ID가 존재하지 않습니다")
                ArticleDetailScreen(
                    articleId = articleId,
                    onBack = { navController.popBackStack() }
                )
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
                    onBack = { navController.popBackStack() },
                    onArticleClick = { articleId ->
                        navController.navigate("ArticleDetail/$articleId")
                    }
                )
            }
        }
    }
}

private fun NavController.navigateToBottomTab(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?,
    isGuestMode: Boolean = false,
    onNavigateToLogin: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationItem.Feed,
        BottomNavigationItem.Subscription,
        BottomNavigationItem.Home,
        BottomNavigationItem.Bookmark,
        BottomNavigationItem.MyPage,
    )

    Surface(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 32.dp),
        shape = RoundedCornerShape(44.dp),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(0, 0, 0, 0),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    modifier = Modifier.height(56.dp),
                    icon = {
                        val isSelected = currentRoute == item.route
                        Icon(
                            painter = if (isSelected) painterResource(item.selectedIcon)
                            else painterResource(item.icon),
                            contentDescription = null,
                            tint = if (isSelected) Color.Unspecified
                            else Caption_Alternative
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            maxLines = 1,
                            fontSize = if (item is BottomNavigationItem.MyPage) 10.sp else 12.sp,
                            fontWeight = FontWeight.Medium
                        )
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
                        if (isGuestMode && item is BottomNavigationItem.MyPage) {
                            onNavigateToLogin()
                            return@NavigationBarItem
                        }
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