package com.example.domain.repository

import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.model.anime.single.SingleMedia

interface ApiRepository {
    suspend fun getAnimeList(): Response<List<Media>>
    suspend fun getAnimeById(id: Int): Response<SingleMedia>
}