package com.linvia.app.model.social

data class Concert(
    val id: String,
    val artistName: String,
    val venue: String,
    val city: String,
    val country: String,
    val date: Long,
    val ticketUrl: String?,
    val priceRange: String?,
    val imageUrl: String?,
    val genre: String?
)
