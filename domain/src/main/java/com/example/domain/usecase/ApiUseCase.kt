package com.example.domain.usecase

import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.repository.ApiRepository

class ApiUseCase(
    private val repository: ApiRepository
) {
    suspend fun getAnimeList(): Response<List<Media>> = repository.getAnimeList()
}