package com.linvia.app.model.media

data class Podcast(
    val id: String,
    val title: String,
    val description: String?,
    val coverUrl: String?,
    val publisher: String,
    val category: String?,
    val episodeCount: Int,
    val totalDurationMs: Long,
    val explicit: Boolean = false,
    val language: String? = null,
    val isSubscribed: Boolean = false,
    val rating: Float? = null
)
