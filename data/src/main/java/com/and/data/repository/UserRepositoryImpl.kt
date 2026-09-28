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
                    // 소셜 로그인 회원은 이메일 가입 전용 필드가 없다. 빈 문자열로 채운다.
                    loginId = response.loginId ?: "",
                    password = response.password ?: "",
                    phoneNumber = response.phoneNumber ?: "",
                    nickname = response.nickname,
                    birthYear = response.birthYear,
                    gender = Gender.getGender(response.gender),
                    emailIndex = response.emailIndex ?: "",
                    subscribeEmail = response.subscribeEmail,
                    subscribePassword = response.subscribePassword ?: "",
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

    override suspend fun kakaoLogin(authorizationCode: String, idToken: String?): KakaoLoginResult {
        val response = handleApiCall(
            apiCall = {
                kakaoLoginApi.kakaoLogin(
                    KakaoLoginRequestDto(
                        provider = PROVIDER_KAKAO,
                        platform = PLATFORM_ANDROID,
                        idToken = idToken,
                        authorizationCode = authorizationCode
                    )
                )
            },
            mapper = { it }
        )

        return if (response.isRegistered) {
            // 기존 회원: accessToken은 서비스 액세스 토큰이다
            val user = response.user
                ?: throw IllegalStateException("isRegistered=true인데 user가 없습니다")
            authPreferenceStore.saveAccessToken(response.accessToken)
            authPreferenceStore.saveGuestMode(false)
            KakaoLoginResult.Success(userMapper.mapToDomain(user))
        } else {
            // 신규 회원: accessToken 필드에 signupToken이 담겨 온다.
            // 이 값을 저장하면 인증 헤더로 나가 이후 모든 API가 깨지므로 절대 저장하지 않는다.
            KakaoLoginResult.NeedSignup(signupToken = response.accessToken)
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

    companion object {
        /** 서버가 값을 바꾸면 이 두 상수만 수정하면 된다 */
        private const val PROVIDER_KAKAO = "KAKAO"
        private const val PLATFORM_ANDROID = "ANDROID"
    }
}
