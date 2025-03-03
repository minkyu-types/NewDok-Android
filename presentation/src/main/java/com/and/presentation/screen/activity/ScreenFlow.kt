package com.and.presentation.screen.activity

enum class ScreenFlow(val route: String) {
    ON_BOARDING("onboardingFlow"),
    LOGIN("loginFlow"),
    REGISTER("registerFlow"),
    PRE_INVESTIGATION("investigationFlow"),
    MAIN("mainFlow")
}