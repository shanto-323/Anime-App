package com.example.network.reposotory

import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.model.anime.single.SingleMedia
import com.example.domain.network.ApiService
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun getAnimeList(): Response<List<Media>> = apiService.getAnimeList()

    override suspend fun getAnimeById(id: Int): Response<SingleMedia> {
        TODO("Not yet implemented")
    }
}