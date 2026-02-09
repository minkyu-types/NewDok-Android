package com.and.data.repository

import com.and.domain.util.ApiException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseRepository(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * # inline 키워드 사용한 이유
     * 일반 함수일 경우에는 호출 시 람다를 전달할 때마다 내부적으로 익명 클래스가 생성되므로,
     * 본문을 호출부에 삽입해서 그 과정을 스킵하여 메모리적 이점 챙길 수 있어서
     */
    protected suspend fun <T, R> handleApiCall(
        apiCall: suspend () -> T,
        mapper: (T) -> R
    ): R = withContext(ioDispatcher) {
        try {
            val dto: T = apiCall()
            mapper(dto)
        } catch (e: HttpException) {
            e.printStackTrace()
            val rawErrorBody = e.response()?.errorBody()?.string().orEmpty()
            val msg = runCatching {
                JSONObject(rawErrorBody).optString("message", e.message())
            }.getOrDefault(e.message())
            throw ApiException(e.code(), msg)
        }  catch (e: IOException) {
            e.printStackTrace()
            if (e.message?.contains("Canceled", ignoreCase = true) == true) {
                throw CancellationException("OkHttp call canceled", e)
            }
            throw ApiException(-1, "네트워크 오류")
        } catch (e: Exception) {
            e.printStackTrace()
            throw ApiException(-1, e.message)
        }
    }

    protected fun <T, R> handleApiFlow(
        apiCall: suspend () -> T,
        mapper: (T) -> R
    ): Flow<R> = flow {
        try {
            val dto = withContext(ioDispatcher) { apiCall() }
            emit(mapper(dto))
        } catch (e: HttpException) {
            val rawErrorBody = e.response()?.errorBody()?.string().orEmpty()
            val msg = runCatching {
                JSONObject(rawErrorBody).optString("message", e.message())
            }.getOrDefault(e.message())
//            throw ApiException(e.code(), msg)
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
            if (e.message?.contains("Canceled", ignoreCase = true) == true) {
                return@flow
            } else {
//                throw ApiException(-1, "네트워크 오류")
            }
        } catch (e: Exception) {
            e.printStackTrace()
//            throw ApiException(-1, "알 수 없는 오류")
        }
    }
}