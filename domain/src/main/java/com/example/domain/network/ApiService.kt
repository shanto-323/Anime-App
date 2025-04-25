package com.example.domain.network

import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.model.anime.single.SingleMedia

interface ApiService {
    suspend fun getAnimeList(): Response<List<Media>>
    suspend fun getAnimeById(id: Int): Response<SingleMedia>
}