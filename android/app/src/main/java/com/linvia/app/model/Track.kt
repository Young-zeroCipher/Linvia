package com.linvia.app.model

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val album: String?,
    val albumArtUrl: String?,
    val audioUrl: String?,
    val durationMs: Long,
    val genre: String?,
    val releaseYear: Int?,
    val explicit: Boolean = false,
    val isDownloaded: Boolean = false,
    val isLiked: Boolean = false,
    val playCount: Int = 0,
    val lyrics: String? = null,
    val translatedLyrics: String? = null,
    val bpm: Int? = null,
    val key: String? = null
)
