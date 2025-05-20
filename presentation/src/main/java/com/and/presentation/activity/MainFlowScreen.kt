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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.and.domain.model.type.IndustryCategory
import com.and.presentation.screen.alarm.AlarmScreen
import com.and.presentation.screen.articledetail.ArticleDetailScreen
import com.and.presentation.screen.bookmark.BookmarkScreen
import com.and.presentation.screen.feed.FeedScreen
import com.and.presentation.screen.home.HomeScreen
import com.and.presentation.screen.mypage.account.AccountManagerScreen
import com.and.presentation.screen.mypage.FaqScreen
import com.and.presentation.screen.mypage.profile.IndustryEditScreen
import com.and.presentation.screen.mypage.profile.InterestEditScreen
import com.and.presentation.screen.mypage.MyPageScreen
import com.and.presentation.screen.mypage.profile.NicknameEditScreen
import com.and.presentation.screen.mypage.notification.NotificationSettingScreen
import com.and.presentation.screen.mypage.profile.ProfileEditScreen
import com.and.presentation.screen.mypage.ServiceFeedbackScreen
import com.and.presentation.screen.mypage.TermsScreen
import com.and.presentation.screen.newsletterdetail.NewsLetterDetailScreen
import com.and.presentation.screen.search.SearchScreen
import com.and.presentation.screen.subscription.SubscriptionScreen
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Primary_Normal

@Composable
fun MainFlowScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
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
                    onArticleClick = {
                        navController.navigate("ArticleDetail")
                    }
                )
            }

            composable("NotificationMain") {
                AlarmScreen(
                    onBack = { navController.popBackStack() },
                    onArticleClick = {
                        navController.navigate("ArticleDetail")
                    },
                    onActionClick = {
                        navController.navigate("NotificationSetting")
                    }
                )
            }
            composable("NotificationSetting") {
                NotificationSettingScreen(
                    onBack = { navController.popBackStack() }
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
                    onArticleClick = {
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
                BookmarkScreen()
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
                AccountManagerScreen(
                    onBack = { navController.popBackStack() },
                    onLogout = {

                    }
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
                    onIndustryClick = {
                        navController.navigate("IndustryEdit")
                    },
                    onInterestClick = {
                        navController.navigate("InterestEdit")
                    }
                )
            }

            composable("NicknameEdit") {
                NicknameEditScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("IndustryEdit") {
                IndustryEditScreen(
                    industry = IndustryCategory.FASHION,
                    onBack = { navController.popBackStack() }
                )
            }

            composable("InterestEdit") {
                InterestEditScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("ArticleDetail") {
                ArticleDetailScreen(
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
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
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
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
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