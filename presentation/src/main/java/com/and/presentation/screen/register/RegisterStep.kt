package com.and.presentation.screen.register

enum class RegisterStep(val route: String, val step: Int) {
    STEP_1_AUTH("step1_auth", 1),
    STEP_2_ID("step2_id", 2),
    STEP_3_PASSWORD("step2_password", 3),
    STEP_4_USER_INFO("step3_user_info", 4),
    STEP_5_TERMS("step4_terms", 5),
    STEP_6_COMPLETE("step5_complete", 6);

    companion object {
        fun getStepByRoute(route: String): Int {
            return entries.firstOrNull() { it.route == route }?.step
                ?: throw IllegalArgumentException("잘못된 route 값입니다: $route")
        }
    }
}