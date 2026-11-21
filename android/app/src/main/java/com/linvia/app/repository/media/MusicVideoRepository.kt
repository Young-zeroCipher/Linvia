package com.linvia.app.repository.media

import com.linvia.app.model.media.MusicVideo
import kotlinx.coroutines.flow.Flow

interface MusicVideoRepository {
    getFeaturedVideos(): Flow<List<MusicVideo>>
    getVideosByArtist(artistId: String): Flow<List<MusicVideo>>
    searchVideos(query: String): Flow<List<MusicVideo>>
    getTrendingVideos(): Flow<List<MusicVideo>>
}
