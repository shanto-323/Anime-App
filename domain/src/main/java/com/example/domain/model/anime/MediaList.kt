package com.example.domain.model.anime


// User Media List
data class MediaList(
    val name: String,  // e.g. "Watching", "Completed"
    val entries: List<Media>
)
