package com.and.presentation.activity

enum class ScreenFlow(val route: String) {
    ON_BOARDING("onboardingFlow"),
    SOCIAL_LOGIN("socialLoginFlow"),
    LOGIN("loginFlow"),
    REGISTER("registerFlow"),
    PRE_INVESTIGATION("investigationFlow"),
    MAIN("mainFlow")
}