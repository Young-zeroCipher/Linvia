package com.linvia.app.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String?,
    val subscriptionTier: String = "free", // free, premium, family
    val createdAt: Long = System.currentTimeMillis()
)
