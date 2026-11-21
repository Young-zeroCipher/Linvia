package com.linvia.app.model

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val coverUrl: String?,
    val releaseYear: Int?,
    val trackCount: Int,
    val genre: String?
)
