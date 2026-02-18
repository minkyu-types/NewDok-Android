package com.and.data.repository

import com.and.data.api.util.GetOptionsApi
import com.and.domain.model.Industry
import com.and.domain.model.Interest
import com.and.domain.repository.OptionsRepository
import javax.inject.Inject

class OptionsRepositoryImpl @Inject constructor(
    private val getOptionsApi: GetOptionsApi
) : OptionsRepository, BaseRepository() {

    override suspend fun getIndustries(): List<Industry> {
        return handleApiCall(
            apiCall = {
                getOptionsApi.getOptions()
            },
            mapper = { response ->
                response.industries.map { dto ->
                    Industry(
                        id = dto.id,
                        name = dto.name
                    )
                }
            }
        )
    }

    override suspend fun getInterests(): List<Interest> {
        return handleApiCall(
            apiCall = {
                getOptionsApi.getOptions()
            },
            mapper = { response ->
                response.interests.map { dto ->
                    Interest(
                        id = dto.id,
                        name = dto.name
                    )
                }
            }
        )
    }
}
