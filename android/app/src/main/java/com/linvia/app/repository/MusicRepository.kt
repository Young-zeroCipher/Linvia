package com.linvia.app.repository

import com.linvia.app.model.Album
import com.linvia.app.model.Artist
import com.linvia.app.model.Playlist
import com.linvia.app.model.Track
import com.linvia.app.model.User
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getFeaturedTracks(): Flow<List<Track>>
    fun getNewReleases(): Flow<List<Album>>
    fun getPlaylists(): Flow<List<Playlist>>
    fun getArtists(): Flow<List<Artist>>
    fun getCurrentUser(): Flow<User?>
    fun searchTracks(query: String): Flow<List<Track>>
    fun searchArtists(query: String): Flow<List<Artist>>
    fun searchAlbums(query: String): Flow<List<Album>>
    fun getTrackById(id: String): Flow<Track?>
    fun getLyrics(trackId: String): Flow<String?>
    fun toggleLike(trackId: String): Flow<Boolean>
    fun downloadTrack(track: Track): Flow<Boolean>
    fun getDownloadedTracks(): Flow<List<Track>>
    fun getAIRRecommendations(userId: String): Flow<List<Track>>
    fun createSocialListeningRoom(roomName: String): Flow<String>
    fun joinSocialListeningRoom(roomId: String): Flow<Boolean>
}
