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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.and.presentation.screen.bookmark.BookmarkScreen
import com.and.presentation.screen.feed.FeedScreen
import com.and.presentation.screen.home.HomeScreen
import com.and.presentation.screen.mypage.MyPageScreen
import com.and.presentation.screen.subscription.SubscriptionScreen
import com.and.presentation.ui.Caption_Alternative
import com.and.presentation.ui.Line_Disabled
import com.and.presentation.ui.Primary_Normal

@Composable
fun MainFlowScreen(
    modifier: Modifier = Modifier
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavigationItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavigationItem.Feed.route) {
                FeedScreen(

                )
            }
            composable(BottomNavigationItem.Subscription.route) {
                SubscriptionScreen(

                )
            }
            composable(BottomNavigationItem.Home.route) {
                HomeScreen(

                )
            }
            composable(BottomNavigationItem.Bookmark.route) {
                BookmarkScreen(

                )
            }
            composable(BottomNavigationItem.MyPage.route) {
                MyPageScreen(

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
        HorizontalDivider(modifier = Modifier
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