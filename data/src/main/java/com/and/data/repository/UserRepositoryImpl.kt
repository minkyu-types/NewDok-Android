package com.and.data.repository

import com.and.data.api.user.GetPreInvestigateNewsLettersApi
import com.and.data.api.user.GetUserByPhoneNumberApi
import com.and.data.api.user.GetUserIdDuplicationApi
import com.and.data.api.user.GetUserInfoApi
import com.and.data.api.user.PatchUserIndustryApi
import com.and.data.api.user.PatchUserInterestsApi
import com.and.data.api.user.PatchUserNicknameApi
import com.and.data.api.user.PatchUserPasswordApi
import com.and.data.api.user.PatchUserPhoneNumberApi
import com.and.data.api.user.PostLoginApi
import com.and.data.api.user.PostSignUpApi
import com.and.data.mapper.NewsLetterMapper
import com.and.data.mapper.UserMapper
import com.and.data.model.request.LoginRequestDto
import com.and.data.model.request.PatchUserIndustryRequestDto
import com.and.data.model.request.PatchUserInterestRequestDto
import com.and.data.model.request.PatchUserNicknameRequestDto
import com.and.data.model.request.PatchUserPasswordRequestDto
import com.and.data.model.request.PatchUserPhoneNumberRequestDto
import com.and.data.model.request.SignUpRequestDto
import com.and.data.preference.AuthPreferenceStore
import com.and.domain.model.Account
import com.and.domain.model.NewsLetter
import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preInvestigateNewsLettersApi: GetPreInvestigateNewsLettersApi,
    private val getUserByPhoneNumberApi: GetUserByPhoneNumberApi,
    private val getUserIdDuplicationApi: GetUserIdDuplicationApi,
    private val getUserInfoApi: GetUserInfoApi,
    private val updateUserIndustryApi: PatchUserIndustryApi,
    private val patchUserInterestsApi: PatchUserInterestsApi,
    private val patchUserNicknameApi: PatchUserNicknameApi,
    private val updateUserPasswordApi: PatchUserPasswordApi,
    private val updateUserPhoneNumberApi: PatchUserPhoneNumberApi,
    private val loginApi: PostLoginApi,
    private val signupApi: PostSignUpApi,
    private val userMapper: UserMapper,
    private val newsLetterMapper: NewsLetterMapper,
    private val authPreferenceStore: AuthPreferenceStore
) : UserRepository, BaseRepository() {
    override fun getUserAccessToken(): Flow<String?> {
        return authPreferenceStore.getAccessToken()
    }

    override suspend fun deleteUserAccessToken(): Boolean {
        return handleApiCall(
            apiCall = {
                authPreferenceStore.clearAccessToken()
            },
            mapper = { result ->
                val a = result
                true
            }
        )
    }

    override suspend fun getPreInvestigateNewsLetters(
        industry: IndustryCategory,
        interests: List<InterestCategory>
    ): List<NewsLetter> {
        return handleApiCall(
            apiCall = {
                val industryName = industry.name
                val interestName = interests.map { it.value }
                preInvestigateNewsLettersApi.getPreInvestigateNewsLetters(
                    industryName,
                    interestName
                ).data
            },
            mapper = { newsLetters ->
                newsLetters.map { newsLetter ->
                    newsLetterMapper.mapToDomain(newsLetter)
                }
            }
        )
    }

    override suspend fun getUserByPhoneNumber(phoneNumber: String): List<User> {
        return handleApiCall(
            apiCall = {
                getUserByPhoneNumberApi.getUserByPhoneNumber(phoneNumber)
            },
            mapper = { users ->
                users.data.map { user ->
                    userMapper.mapToDomain(user)
                }
            }
        )
    }

    override suspend fun getUserIdDuplication(loginId: String): Account {
        return handleApiCall(
            apiCall = {
                getUserIdDuplicationApi.getUserIdDuplication(loginId)
            },
            mapper = { response ->
                Account(
                    id = response.id,
                    loginId = response.loginId,
                    phoneNumber = response.phoneNumber,
                    createdAt = response.createdAt,
                )
            }
        )
    }

    override suspend fun getUserInfo(): User {
        return handleApiCall(
            apiCall = {
                getUserInfoApi.getUserInfo()
            },
            mapper = { response ->
                User(
                    id = response.id,
                    loginId = response.loginId,
                    password = response.password,
                    phoneNumber = response.phoneNumber,
                    nickname = response.nickname,
                    birthYear = response.birthYear,
                    gender = Gender.getGender(response.gender),
                    emailIndex = response.emailIndex,
                    subscribeEmail = response.subscribeEmail,
                    subscribePassword = response.subscribePassword,
                    createdAt = response.createdAt,
                    industryId = response.industryId,
                    interests = response.interests.map {
                        InterestCategory.getInterestById(it.interestId)
                    },
                )
            }
        )
    }

    /**
     * TODO
     * 아웃풋을 어떻게 소비할 건지 결정하기
     */
    override suspend fun updateUserIndustry(industryId: Int) {
        return handleApiCall(
            apiCall = {
                updateUserIndustryApi.patchUserIndustry(
                    PatchUserIndustryRequestDto(
                        industryId
                    )
                )
            },
            mapper = { response ->
                // id, loginId, industryId
            }
        )
    }

    /**
     * TODO
     * 아웃풋을 어떻게 소비할 건지 결정하기
     */
    override suspend fun updateUserInterests(interestIds: List<Int>) {
        return handleApiCall(
            apiCall = {
                patchUserInterestsApi.patchUserInterests(
                    PatchUserInterestRequestDto(
                        interestIds
                    )
                )
            },
            mapper = { response ->

            }
        )
    }

    override suspend fun updateUserNickname(nickname: String): Boolean {
        return handleApiCall(
            apiCall = {
                patchUserNicknameApi.patchUserNickname(
                    PatchUserNicknameRequestDto(
                        nickname = nickname
                    )
                )
            },
            mapper = { response ->
                response.isNicknameChanged
            }
        )
    }

    /**
     * TODO
     * 아웃풋을 어떻게 소비할 건지 결정하기
     */
    override suspend fun updateUserPassword(
        loginId: String,
        prevPassword: String,
        password: String
    ): Boolean {
        return handleApiCall(
            apiCall = {
                updateUserPasswordApi.patchUserPassword(
                    PatchUserPasswordRequestDto(
                        loginId = loginId,
                        prevPassword = prevPassword,
                        password = password
                    )
                )
            },
            mapper = { response ->
                true
            }
        )
    }

    override suspend fun updateUserPhoneNumber(phoneNumber: String): Boolean {
        return handleApiCall(
            apiCall = {
                updateUserPhoneNumberApi.patchUserPhoneNumber(
                    PatchUserPhoneNumberRequestDto(
                        phoneNumber = phoneNumber
                    )
                )
            },
            mapper = { response ->
                response.isPhoneNumberChanged
            }
        )
    }

    override suspend fun login(loginId: String, password: String): User {
        return handleApiCall(
            apiCall = {
                loginApi.login(
                    LoginRequestDto(
                        loginId = loginId,
                        password = password
                    )
                ).also {
                    authPreferenceStore.saveAccessToken(it.accessToken)
                }
            },
            mapper = { response ->
                userMapper.mapToDomain(response.user)
            }
        )
    }

    override suspend fun signUp(
        loginId: String,
        password: String,
        phoneNumber: String,
        nickname: String,
        birthYear: String,
        gender: Gender
    ): User {
        return handleApiCall(
            apiCall = {
                signupApi.signUp(
                    SignUpRequestDto(
                        loginId = loginId,
                        password = password,
                        phoneNumber = phoneNumber,
                        nickname = nickname,
                        birthYear = birthYear,
                        gender = when (gender) {
                            Gender.MALE -> "남자"
                            Gender.FEMALE -> "여자"
                        }
                    )
                )
            },
            mapper = { response ->
                userMapper.mapToDomain(response.user)
            }
        )
    }
}