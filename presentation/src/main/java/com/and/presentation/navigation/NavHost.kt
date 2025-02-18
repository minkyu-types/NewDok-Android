package com.and.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.presentation.onboarding.OnboardingScreen

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen(navController = navController)
        }
    }
}