package com.and.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.repository.SearchRepository
import com.and.domain.util.ApiException
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
    private val searchRepository: SearchRepository
): ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _searchResult = mutableStateOf<SearchResultModel.MemberSearchResultModel?>(null)
    val searchResult: State<SearchResultModel.MemberSearchResultModel?> = _searchResult

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val searchResultFlow: Flow<SearchResultModel.MemberSearchResultModel> =
        queryFlow
            .filter { it.isNotEmpty() }
            .debounce(100L)
            .flatMapLatest { query ->
                val newsLetterFlow =searchRepository.getNewsLetterSearchResult(query).map { result ->
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
                }
            }

    init {
        viewModelScope.launch {
            searchResultFlow.collectLatest { result ->
                _searchResult.value = result
            }
        }
    }

    fun setQuery(query: String) {
        queryFlow.update { query }
    }
}