package com.example.network.network

import com.apollographql.apollo.ApolloClient
import com.example.domain.model.Response
import com.example.domain.model.anime.Media
import com.example.domain.model.anime.MediaListCollection
import com.example.domain.model.anime.single.SingleMedia
import com.example.domain.network.ApiService
import com.example.network.GetAnimeListQuery
import com.example.network.model.toDomainMedia
import com.example.network.type.MediaType
import org.koin.core.qualifier.named


class ApiServiceImpl(
    private val apolloClient: ApolloClient
) : ApiService {
    override suspend fun getAnimeList(): Response<List<Media>> {
        return try {
            val list: MutableList<Media> = mutableListOf()
            apolloClient
                .query(GetAnimeListQuery(MediaType.ANIME, 12345))
                .execute()
                .data
                ?.MediaListCollection.let { mediaListCollection ->
                    mediaListCollection?.lists?.let { entries ->
                        for (entry in entries) {
                            entry?.entries?.let { animes ->
                                for (anime in animes) {
                                    anime?.media?.let {
                                        list.add(it.toDomainMedia())
                                    }
                                }
                            }
                        }
                    }
                }
            Response.Success(list)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Something went wrong")
        }

    }

    override suspend fun getAnimeById(id: Int): Response<SingleMedia> {
        TODO("Not yet implemented")
    }
}