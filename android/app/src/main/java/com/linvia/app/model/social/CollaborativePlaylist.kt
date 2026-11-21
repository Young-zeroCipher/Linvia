package com.linvia.app.model.social

import com.linvia.app.model.Track

data class CollaborativePlaylist(
    val id: String,
    val name: String,
    val description: String?,
    val coverUrl: String?,
    val ownerId: String,
    val memberIds: List<String> = emptyList(),
    val tracks: List<Track> = emptyList(),
    val isPublic: Boolean = true,
    val inviteCode: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis()
)
