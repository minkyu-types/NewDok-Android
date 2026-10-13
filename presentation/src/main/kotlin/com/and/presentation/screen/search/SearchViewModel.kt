package com.and.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.repository.SearchRepository
import com.and.domain.usecase.search.GetPopularKeywordsUseCase
import com.and.presentation.model.PopularKeywordModel
import com.and.presentation.model.SearchResultModel
import com.and.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val getPopularKeywordsUseCase: GetPopularKeywordsUseCase
): ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _searchResult = mutableStateOf<SearchResultModel.MemberSearchResultModel?>(null)
    val searchResult: State<SearchResultModel.MemberSearchResultModel?> = _searchResult

    /** 조회 전이거나 실패한 경우 null. 화면은 이때 인기 검색어 영역을 그리지 않는다 */
    private val _popularKeywords = mutableStateOf<PopularKeywordModel?>(null)
    val popularKeywords: State<PopularKeywordModel?> = _popularKeywords

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val searchResultFlow: Flow<SearchResultModel.MemberSearchResultModel> =
        queryFlow
            .filter { it.isNotEmpty() }
            .debounce(300L)
            .flatMapLatest { query ->
                val newsLetterFlow = searchRepository.getNewsLetterSearchResult(query).map { result ->
                    result.toPresentation()
                }
                val articlesFlow = searchRepository.getArticleSearchResult(query).map { result ->
                    result.map {
                        it.toPresentation()
                    }
                }

                combine(newsLetterFlow, articlesFlow) { newsLetter, articles ->
                    SearchResultModel.MemberSearchResultModel(
                        message = "${query}에 대한 ${articles.size}개의 검색 결과를 찾았습니다.",
                        newsLetter = newsLetter,
                        articles = articles
                    )
                }.catch { e ->
                    // catch를 쿼리별 내부 flow에 두어야 상위 스트림이 종료되지 않고
                    // 이후 검색어 입력이 계속 동작한다
                    e.printStackTrace()
                    emit(
                        SearchResultModel.MemberSearchResultModel.EMPTY.copy(
                            message = "검색 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요."
                        )
                    )
                }
            }

    init {
        viewModelScope.launch {
            searchResultFlow.collectLatest { result ->
                _searchResult.value = result
            }
        }
        getPopularKeywords()
    }

    private fun getPopularKeywords() {
        viewModelScope.launch {
            getPopularKeywordsUseCase(Unit)
                .map { it.toPresentation() }
                .catch { e ->
                    // 인기 검색어는 부가 정보이므로, 실패해도 검색 자체는 계속 쓸 수 있도록
                    // 상태를 null로 두고 넘어간다
                    e.printStackTrace()
                }
                .collectLatest { result ->
                    _popularKeywords.value = result
                }
        }
    }

    fun setQuery(query: String) {
        queryFlow.update { query }
    }
}