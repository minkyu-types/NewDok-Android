package com.and.data.repository

import com.and.data.api.newsletter.GetNonMemberNewsLetterByIdApi
import com.and.data.api.newsletter.GetNonMemberNewsLettersApi
import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.NonMemberNewsLetterRepository
import java.time.Instant
import javax.inject.Inject

class NonMemberNewsLetterRepositoryImpl @Inject constructor(
    private val getNewsLetterByIdApi: GetNonMemberNewsLetterByIdApi,
    private val getNewsLettersApi: GetNonMemberNewsLettersApi
): NonMemberNewsLetterRepository {
    override suspend fun getNewsLetters(
        orderOption: SortCategory,
        industries: List<IndustryCategory>,
        dates: List<Instant>
    ): List<NewsLetter> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsLetterById(newsLetterId: Int): NewsLetter {
        TODO("Not yet implemented")
    }
}