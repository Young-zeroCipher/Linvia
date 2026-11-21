package com.linvia.app.repository.social

import com.linvia.app.model.social.CollaborativePlaylist
import com.linvia.app.model.social.Concert
import com.linvia.app.model.social.Room
import com.linvia.app.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SocialRepositoryImpl(private val apiService: ApiService) : SocialRepository {

    override fun createRoom(name: String): Flow<String> = flow {
        emit("room_123")
    }

    override fun joinRoom(inviteCode: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getActiveRooms(): Flow<List<Room>> = flow {
        emit(emptyList())
    }

    override fun getRoomById(id: String): Flow<Room?> = flow {
        emit(null)
    }

    override fun sendMessage(roomId: String, message: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun createCollaborativePlaylist(name: String, description: String?): Flow<String> = flow {
        emit("playlist_123")
    }

    override fun joinCollaborativePlaylist(inviteCode: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getCollaborativePlaylists(): Flow<List<CollaborativePlaylist>> = flow {
        emit(emptyList())
    }

    override fun addTrackToCollaborativePlaylist(playlistId: String, trackId: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getUpcomingConcerts(): Flow<List<Concert>> = flow {
        emit(emptyList())
    }

    override fun getConcertsByArtist(artistId: String): Flow<List<Concert>> = flow {
        emit(emptyList())
    }

    override fun buyConcertTicket(concertId: String): Flow<Boolean> = flow {
        emit(true)
    }
}
