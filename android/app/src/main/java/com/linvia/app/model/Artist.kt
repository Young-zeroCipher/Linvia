package com.linvia.app.model

data class Artist(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val genre: String?,
    val monthlyListeners: Long?,
    val followerCount: Int = 0,
    val isFollowed: Boolean = false
)
