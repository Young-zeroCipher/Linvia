package com.linvia.app.model.media

data class MusicVideo(
    val id: String,
    val trackId: String,
    val title: String,
    val artist: String,
    val videoUrl: String?,
    val thumbnailUrl: String?,
    val durationMs: Long,
    val resolution: String = "1080p",
    val explicit: Boolean = false,
    val viewCount: Long = 0,
    val likeCount: Int = 0
)
