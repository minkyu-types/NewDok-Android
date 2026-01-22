package com.and.presentation.screen.preinvestigation

/**
 * 사전 조사 단계를 나타내는 enum 클래스
 *
 * 1단계: 종사 산업
 * 2단계: 관심사
 * 3단계: 뉴스레터 큐레이션
 *
 * @author 로키
 */
enum class InvestigationStep(val route: String, val step: Int) {
    STEP_1_INDUSTRY("investigation_step1_industry", 1),
    STEP_2_INTERESTS("investigation_step2_interests", 2),
    STEP_3_NEWSLETTER("investigation_step3_newsletter_curation", 3);

    companion object {
        fun getStepByRoute(route: String): Int {
            return InvestigationStep.entries.firstOrNull() { it.route == route }?.step
                ?: throw IllegalArgumentException("잘못된 route 값입니다: $route")
        }
    }
}