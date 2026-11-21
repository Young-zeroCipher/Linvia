package com.linvia.app.model

data class Playlist(
    val id: String,
    val name: String,
    val description: String?,
    val coverUrl: String?,
    val trackCount: Int,
    val ownerId: String?,
    val isCollaborative: Boolean = false,
    val isPublic: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
