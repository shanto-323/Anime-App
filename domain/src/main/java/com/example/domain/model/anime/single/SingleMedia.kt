package com.example.domain.model.anime.single

// Media Entry
data class SingleMedia(
    val id: Int,
    val title: SingleMediaTitle,
    val genres: List<String>,
    val coverImage: SingleCoverImage,
    val type: String,  // "ANIME" or "MANGA"
    val isAdult: Boolean,
    val description : String,
    val format : String,
    val bannerImage:String,
    val averageScore: Int
)