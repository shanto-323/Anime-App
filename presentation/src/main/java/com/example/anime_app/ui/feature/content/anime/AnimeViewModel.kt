package com.example.anime_app.ui.feature.content.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app.model.UiResponse
import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.usecase.ApiUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val apiUseCase: ApiUseCase
) : ViewModel() {
    private val _response = MutableStateFlow<UiResponse<*>>(UiResponse.Nothing)
    val response = _response.asStateFlow()

    init {
        getAnimeList()
    }

    private fun getAnimeList() {
        _response.value = UiResponse.Loading
        viewModelScope.launch {
            when (val apiCall = apiUseCase.getAnimeList()) {
                is Response.Error -> {
                    _response.value = UiResponse.Error(apiCall.msg)
                    delay(1000)
                    _response.value = UiResponse.Nothing
                }

                is Response.Success<List<Media>> -> {
                    _response.value = UiResponse.Success(data = apiCall.data)
                }
            }
        }
    }
}