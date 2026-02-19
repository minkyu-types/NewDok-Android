package com.and.data.repository

import com.and.data.api.newsletter.GetNonMemberNewsLetterByIdApi
import com.and.data.api.newsletter.GetNonMemberNewsLettersApi
import com.and.data.mapper.NewsLetterDetailMapper
import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.SimpleArticle
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.MemberNewsLetterRepository
import javax.inject.Inject

class NonMemberNewsLetterRepositoryImpl @Inject constructor(
    private val getNewsLetterByIdApi: GetNonMemberNewsLetterByIdApi,
    private val getNewsLettersApi: GetNonMemberNewsLettersApi,
    private val newsLetterDetailMapper: NewsLetterDetailMapper
) : MemberNewsLetterRepository, BaseRepository() {

    override suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        dayId: Int
    ): List<NewsLetter> {
        return handleApiCall(
            apiCall = {
                getNewsLettersApi.getNewsLetters(
                    orderOpt = orderOption.value,
                    industry = listOf(industry.id.toString()),
                    day = listOf(dayId.toString())
                )
            },
            mapper = { response ->
                response.map { newsLetter ->
                    newsLetterDetailMapper.mapToDomain(newsLetter)
                }
            }
        )
    }

    override suspend fun getNewsLetterById(newsLetterId: Int): NewsLetter {
        return handleApiCall(
            apiCall = {
                getNewsLetterByIdApi.getNewsLettersById(newsLetterId.toString())
            },
            mapper = { response ->
                NewsLetter(
                    brandId = response.brandId,
                    brandName = response.brandName,
                    imageUrl = response.imageUrl,
                    interests = response.interests.mapNotNull {
                        InterestCategory.getInterestByValue(it.name)
                    },
                    articles = response.brandArticleList.map {
                        SimpleArticle(
                            it.id,
                            it.title,
                            it.date
                        )
                    },
                    detailDescription = response.detailDescription,
                    publicationCycle = response.publicationCycle,
                    subscribeUrl = response.subscribeUrl,
                    isSubscribed = response.isSubscribed,
                )
            }
        )
    }

    override suspend fun getRecommendedNewsLetters(type: RecommendedNewsLetterType): List<RecommendedNewsLetter> {
        throw UnsupportedOperationException("비회원은 추천 뉴스레터를 이용할 수 없습니다.")
    }

    override suspend fun getSubscribedNewsLetters(): List<BriefNewsLetter> {
        return emptyList()
    }

    override suspend fun getUnSubscribedNewsLetters(): List<BriefNewsLetter> {
        return emptyList()
    }

    override suspend fun updateSubscription(newsLetterId: Int, wasSubscribed: Boolean) {
        throw UnsupportedOperationException("비회원은 구독 관리를 이용할 수 없습니다.")
    }

    override suspend fun getSubscribedNewsLettersCount(): Int {
        return 0
    }
}
