package com.and.data.repository

import com.and.data.preference.AuthPreferenceStore
import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetters
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.MemberNewsLetterRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Named

class NewsLetterRepositoryDelegate @Inject constructor(
    @Named("member") private val memberRepository: MemberNewsLetterRepository,
    @Named("nonMember") private val nonMemberRepository: MemberNewsLetterRepository,
    private val authPreferenceStore: AuthPreferenceStore
) : MemberNewsLetterRepository {

    private suspend fun getRepository(): MemberNewsLetterRepository {
        val token = authPreferenceStore.getAccessToken().firstOrNull()
        return if (!token.isNullOrBlank()) memberRepository else nonMemberRepository
    }

    override suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        dayId: Int
    ): List<NewsLetter> {
        return getRepository().getNewsLetters(orderOption, industry, dayId)
    }

    override suspend fun getNewsLetterById(newsLetterId: Int): NewsLetter {
        return getRepository().getNewsLetterById(newsLetterId)
    }

    override suspend fun getRecommendedNewsLetters(): RecommendedNewsLetters {
        return getRepository().getRecommendedNewsLetters()
    }

    override suspend fun getSearchedNewsLetter(brandName: String): NewsLetter {
        return getRepository().getSearchedNewsLetter(brandName)
    }

    override suspend fun getSubscribedNewsLetters(): List<BriefNewsLetter> {
        return getRepository().getSubscribedNewsLetters()
    }

    override suspend fun getUnSubscribedNewsLetters(): List<BriefNewsLetter> {
        return getRepository().getUnSubscribedNewsLetters()
    }

    override suspend fun updateSubscription(newsLetterId: Int, wasSubscribed: Boolean) {
        return getRepository().updateSubscription(newsLetterId, wasSubscribed)
    }

    override suspend fun getSubscribedNewsLettersCount(): Int {
        return getRepository().getSubscribedNewsLettersCount()
    }
}
