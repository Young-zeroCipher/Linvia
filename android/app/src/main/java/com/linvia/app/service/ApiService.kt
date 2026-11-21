package com.linvia.app.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("tracks/featured")
    suspend fun getFeaturedTracks(): List<com.linvia.app.model.Track>

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): List<com.linvia.app.model.Track>
}
