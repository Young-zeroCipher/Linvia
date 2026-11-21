package com.linvia.app.model.media

data class Audiobook(
    val id: String,
    val title: String,
    val author: String,
    val narrator: String?,
    val coverUrl: String?,
    val category: String?,
    val durationMs: Long,
    val chapterCount: Int,
    val price: Double?,
    val isPurchased: Boolean = false,
    val rating: Float? = null,
    val explicit: Boolean = false
)
