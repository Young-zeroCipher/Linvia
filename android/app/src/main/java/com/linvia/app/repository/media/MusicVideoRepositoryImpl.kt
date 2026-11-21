package com.linvia.app.repository.media

import com.linvia.app.model.media.MusicVideo
import com.linvia.app.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicVideoRepositoryImpl(private val apiService: ApiService) : MusicVideoRepository {

    override fun getFeaturedVideos(): Flow<List<MusicVideo>> = flow {
        emit(emptyList())
    }

    override fun getVideosByArtist(artistId: String): Flow<List<MusicVideo>> = flow {
        emit(emptyList())
    }

    override fun searchVideos(query: String): Flow<List<MusicVideo>> = flow {
        emit(emptyList())
    }

    override fun getTrendingVideos(): Flow<List<MusicVideo>> = flow {
        emit(emptyList())
    }
}
