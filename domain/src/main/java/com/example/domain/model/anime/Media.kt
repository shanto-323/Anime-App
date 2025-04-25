package com.example.domain.model.anime

// Media Entry
data class Media(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val bannerImage:String,
    val type: String,  // "ANIME" or "MANGA"
    val isAdult: Boolean
)