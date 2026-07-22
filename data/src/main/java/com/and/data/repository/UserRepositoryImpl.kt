package com.and.data.repository

import com.and.data.api.auth.PostKakaoLoginApi
import com.and.data.api.auth.PostKakaoSignupApi
import com.and.data.api.user.DeleteUserApi
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
import com.and.data.mapper.KakaoProfileMapper
import com.and.data.mapper.NewsLetterMapper
import com.and.data.mapper.UserMapper
import com.and.data.model.request.AgreementDto
import com.and.data.model.request.KakaoLoginRequestDto
import com.and.data.model.request.KakaoSignupRequestDto
import com.and.data.model.request.LoginRequestDto
import com.and.data.model.request.PatchUserIndustryRequestDto
import com.and.data.model.request.PatchUserInterestRequestDto
import com.and.data.model.request.PatchUserNicknameRequestDto
import com.and.data.model.request.PatchUserPasswordRequestDto
import com.and.data.model.request.PatchUserPhoneNumberRequestDto
import com.and.data.model.request.SignUpRequestDto
import com.and.data.preference.AuthPreferenceStore
import com.and.domain.model.Account
import com.and.domain.model.KakaoLoginResult
import com.and.domain.model.NewsLetter
import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.auth.Agreement
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
    private val kakaoLoginApi: PostKakaoLoginApi,
    private val kakaoSignupApi: PostKakaoSignupApi,
    private val withdrawalApi: DeleteUserApi,
    private val userMapper: UserMapper,
    private val kakaoProfileMapper: KakaoProfileMapper,
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
                result
            }
        )
    }

    override suspend fun setGuestMode(isGuest: Boolean) {
        authPreferenceStore.saveGuestMode(isGuest)
    }

    override fun isGuestMode(): Flow<Boolean> {
        return authPreferenceStore.isGuestMode()
    }

    override suspend fun getPreInvestigateNewsLetters(
        industry: IndustryCategory,
        interests: List<InterestCategory>
    ): List<NewsLetter> {
        return handleApiCall(
            apiCall = {
                val industryName = industry.id.toString()
                val interestName = interests.map { it.id.toString() }
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
                    interests = response.interests.mapNotNull {
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
                response.isNicknameChanged == "Y"
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
                    authPreferenceStore.saveGuestMode(false)
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
    ): String {
        return handleApiCall(
            apiCall = {
                signupApi.signUp(
                    SignUpRequestDto(
                        loginId = loginId,
                        password = password,
                        phoneNumber = phoneNumber,
                        nickname = nickname,
                        birthYear = birthYear,
                        gender = gender.value
                    )
                )
            },
            mapper = { response ->
                response.accessToken
            }
        )
    }

    override suspend fun kakaoLogin(code: String, redirectUri: String): KakaoLoginResult {
        val response = handleApiCall(
            apiCall = {
                kakaoLoginApi.kakaoLogin(
                    KakaoLoginRequestDto(
                        code = code,
                        redirectUri = redirectUri
                    )
                )
            },
            mapper = { it }
        )

        return if (response.isRegistered && response.accessToken != null && response.user != null) {
            // 기존 회원: 액세스 토큰 저장 후 Success 반환
            authPreferenceStore.saveAccessToken(response.accessToken)
            authPreferenceStore.saveGuestMode(false)
            KakaoLoginResult.Success(userMapper.mapToDomain(response.user))
        } else if (!response.isRegistered && response.signupToken != null && response.profile != null) {
            // 신규 회원: NeedSignup 반환 (토큰 저장 안함)
            KakaoLoginResult.NeedSignup(
                signupToken = response.signupToken,
                profile = kakaoProfileMapper.mapToDomain(response.profile)
            )
        } else {
            throw IllegalStateException("Invalid Kakao login response")
        }
    }

    override suspend fun kakaoSignup(
        signupToken: String,
        nickname: String,
        birthYear: String,
        gender: Gender,
        agreements: List<Agreement>
    ): User {
        val response = handleApiCall(
            apiCall = {
                kakaoSignupApi.kakaoSignup(
                    KakaoSignupRequestDto(
                        signupToken = signupToken,
                        nickname = nickname,
                        birthYear = birthYear,
                        gender = gender.value,
                        agreements = agreements.map {
                            AgreementDto(
                                type = it.type.value,
                                agreed = it.agreed
                            )
                        }
                    )
                )
            },
            mapper = { it }
        )

        // 토큰 저장
        authPreferenceStore.saveAccessToken(response.accessToken)
        authPreferenceStore.saveGuestMode(false)

        return userMapper.mapToDomain(response.user)
    }

    override suspend fun withdrawal(): Pair<Boolean, String> {
        return handleApiCall(
            apiCall = {
                withdrawalApi.deleteUser()
            },
            mapper = { response ->
                Pair(true, response.message)
            }
        )
    }
}