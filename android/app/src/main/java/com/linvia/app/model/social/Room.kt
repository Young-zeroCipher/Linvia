package com.linvia.app.model.social

data class Room(
    val id: String,
    val name: String,
    val hostId: String,
    val currentTrackId: String?,
    val positionMs: Long = 0,
    val isPlaying: Boolean = false,
    val memberIds: List<String> = emptyList(),
    val maxMembers: Int = 50,
    val isPublic: Boolean = true,
    val inviteCode: String,
    val createdAt: Long = System.currentTimeMillis(),
    val chatMessages: List<ChatMessage> = emptyList()
)

data class ChatMessage(
    val id: String,
    val userId: String,
    val userName: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
