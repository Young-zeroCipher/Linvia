package com.linvia.app.model.media

data class Episode(
    val id: String,
    val podcastId: String,
    val title: String,
    val description: String?,
    val audioUrl: String?,
    val durationMs: Long,
    val releaseDate: Long,
    val explicit: Boolean = false,
    val isDownloaded: Boolean = false,
    val isPlayed: Boolean = false,
    val positionMs: Long = 0,
    val showNotes: String? = null,
    val transcript: String? = null
)
