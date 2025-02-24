package com.and.presentation.screen.register

enum class RegisterStep(val route: String, val step: Int) {
    STEP_1_AUTH("step1_auth", 1),
    STEP_2_PASSWORD("step2_password", 2),
    STEP_3_USER_INFO("step3_user_info", 3),
    STEP_4_TERMS("step4_terms", 4),
    STEP_5_COMPLETE("step5_complete", 5);

    companion object {
        fun getStepByRoute(route: String): Int {
            return entries.firstOrNull() { it.route == route }?.step
                ?: throw IllegalArgumentException("잘못된 route 값입니다: $route")
        }
    }
}