package com.and.presentation.activity

import com.and.presentation.R

sealed class BottomNavigationItem(val route: String, val label: String, val icon: Int, val selectedIcon: Int) {
    data object Feed : BottomNavigationItem("feed", "둘러보기", R.drawable.ic_line_newsletter, R.drawable.ic_fill_newsletter)
    data object Subscription :
        BottomNavigationItem("subscription", "구독관리", R.drawable.ic_line_mailbox, R.drawable.ic_fill_mailbox)

    data object Home : BottomNavigationItem("home", "홈", R.drawable.ic_line_home, R.drawable.ic_fill_home)
    data object Bookmark : BottomNavigationItem("bookmark", "북마크함", R.drawable.ic_line_bookmark, R.drawable.ic_fill_bookmark)
    data object MyPage : BottomNavigationItem("mypage", "마이페이지", R.drawable.ic_line_user, R.drawable.ic_fill_user)
}