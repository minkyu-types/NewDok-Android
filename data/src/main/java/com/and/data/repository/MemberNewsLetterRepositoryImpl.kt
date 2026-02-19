package com.and.data.repository

import com.and.data.api.newsletter.GetMemberNewsLetterByIdApi
import com.and.data.api.newsletter.GetMemberNewsLettersApi
import com.and.data.api.newsletter.GetRecommendedNewsLettersApi
import com.and.data.api.newsletter.GetSubscribedNewsLettersApi
import com.and.data.api.newsletter.GetSubscribedNewsLettersCountApi
import com.and.data.api.newsletter.GetUnSubscribedNewsLettersApi
import com.and.data.api.newsletter.PatchSubscriptionPauseApi
import com.and.data.api.newsletter.PatchSubscriptionResumeApi
import com.and.data.mapper.BriefNewsLetterMapper
import com.and.data.mapper.NewsLetterDetailMapper
import com.and.data.mapper.RecommendedNewsLetterMapper
import com.and.data.model.request.PatchSubscriptionPauseRequestDto
import com.and.data.model.request.PatchSubscriptionResumeRequestDto
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

class MemberNewsLetterRepositoryImpl @Inject constructor(
    private val getNewsLetterByIdApi: GetMemberNewsLetterByIdApi,
    private val getNewsLettersApi: GetMemberNewsLettersApi,
    private val getRecommendedNewsLettersApi: GetRecommendedNewsLettersApi,
    private val getSubscribedNewsLettersApi: GetSubscribedNewsLettersApi,
    private val getUnSubscribedNewsLettersApi: GetUnSubscribedNewsLettersApi,
    private val patchSubscriptionPauseApi: PatchSubscriptionPauseApi,
    private val patchSubscriptionResumeApi: PatchSubscriptionResumeApi,
    private val briefNewsLetterMapper: BriefNewsLetterMapper,
    private val newsLetterDetailMapper: NewsLetterDetailMapper,
    private val recommendedNewsLetterMapper: RecommendedNewsLetterMapper,
    private val getSubscribedNewsLettersCountApi: GetSubscribedNewsLettersCountApi
): MemberNewsLetterRepository, BaseRepository() {

    companion object {
        private const val NEWSLETTER_SUBSCRIBED = "CONFIRMED"
    }

    override suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        dayId: Int
    ): List<NewsLetter> {
        return handleApiCall(
            apiCall = {
                getNewsLettersApi.getAllNewsLetters(
                    orderOpt = orderOption.value,
                    industry = industry.id.toString(),
                    day = dayId.toString()
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
        return handleApiCall(
            apiCall = {
                when (type) {
                    RecommendedNewsLetterType.INTERSECTION -> getRecommendedNewsLettersApi.getIntersectionNewsLetters()
                    RecommendedNewsLetterType.UNION -> getRecommendedNewsLettersApi.getUnionNewsLetters()
                }
            },
            mapper = { response ->
                response.map { newsLetter ->
                    recommendedNewsLetterMapper.mapToDomain(newsLetter)
                }
            }
        )
    }

    override suspend fun getSubscribedNewsLetters(): List<BriefNewsLetter> {
        return handleApiCall(
            apiCall = {
                getSubscribedNewsLettersApi.getSubscribedNewsLetters()
            },
            mapper = { response ->
                response.map { newsLetter ->
                    briefNewsLetterMapper.mapToDomain(newsLetter)
                }
            }
        )
    }

    override suspend fun getUnSubscribedNewsLetters(): List<BriefNewsLetter> {
        return handleApiCall(
            apiCall = {
                getUnSubscribedNewsLettersApi.getUnSubscribedNewsLetters()
            },
            mapper = { response ->
                response.map { newsLetter ->
                    briefNewsLetterMapper.mapToDomain(newsLetter)
                }
            }
        )
    }

    override suspend fun updateSubscription(newsLetterId: Int, wasSubscribed: Boolean) {
        if (wasSubscribed) {
            patchSubscriptionPauseApi.patchSubscriptionPause(
                PatchSubscriptionPauseRequestDto(
                    newsletterId = newsLetterId.toString()
                )
            )
        } else {
            patchSubscriptionResumeApi.patchSubscriptionResume(
                PatchSubscriptionResumeRequestDto(
                    newsLetterId = newsLetterId.toString()
                )
            )
        }
    }

    override suspend fun getSubscribedNewsLettersCount(): Int {
        return handleApiCall(
            apiCall = {
                getSubscribedNewsLettersCountApi.getSubscribedNewsLetters()
            },
            mapper = { response ->
                response.count
            }
        )
    }
}