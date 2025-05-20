package com.and.data.repository

import com.and.domain.util.ApiException
import org.json.JSONObject
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository {

    /**
     * # inline 키워드 사용한 이유
     * 일반 함수일 경우에는 호출 시 람다를 전달할 때마다 내부적으로 익명 클래스가 생성되므로,
     * 본문을 호출부에 삽입해서 그 과정을 스킵하여 메모리적 이점 챙길 수 있어서
     */
    protected suspend inline fun <T, R> handleApiCall(
        crossinline apiCall: suspend () -> T,
        crossinline mapper: (T) -> R
    ): R {
        return try {
            val dto = apiCall()
            mapper(dto)
        } catch (e: HttpException) {
            e.printStackTrace()
            val raw = e.response()?.errorBody()?.string().orEmpty()
            val msg = runCatching {
                JSONObject(raw).optString("message", e.message())
            }.getOrDefault(e.message())
            throw ApiException(e.code(), msg)
        } catch (e: Exception) {
            e.printStackTrace()
            throw ApiException(-1, "네트워크 오류")
        }
    }
}