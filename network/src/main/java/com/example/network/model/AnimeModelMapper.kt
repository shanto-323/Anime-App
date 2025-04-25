package com.example.network.model

import com.example.domain.model.anime.Media
import com.example.network.GetAnimeListQuery

fun GetAnimeListQuery.Media.toDomainMedia(): Media {
    return Media(
        id = id,
        title = title?.romaji ?: "",
        genres = genres?.mapNotNull { it } ?: emptyList(),
        bannerImage = bannerImage ?: "",
        type = type?.toString() ?: "",
        isAdult = isAdult ?: false
    )
}