package com.and.presentation.screen.register

enum class RegisterStep(val route: String, val step: Int) {
    STEP_1_AUTH("register_step1_auth", 1),
    STEP_2_ID("register_step2_id", 2),
    STEP_3_PASSWORD("register_step2_password", 3),
    STEP_4_USER_INFO("register_step3_user_info", 4),
    STEP_5_TERMS("register_step4_terms", 5),
    STEP_6_COMPLETE("register_step_6_complete/{email}", 6),
    STEP_7_ADDITIONAL_INFO("register_step6_additional_info", 7);

    companion object {
        fun getStepByRoute(route: String): Int {
            return entries.firstOrNull() { it.route == route }?.step
                ?: throw IllegalArgumentException("잘못된 route 값입니다: $route")
        }
    }
}