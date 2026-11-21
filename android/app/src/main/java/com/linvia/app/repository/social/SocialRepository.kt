package com.linvia.app.repository.social

import com.linvia.app.model.social.CollaborativePlaylist
import com.linvia.app.model.social.Concert
import com.linvia.app.model.social.Room
import kotlinx.coroutines.flow.Flow

interface SocialRepository {
    fun createRoom(name: String): Flow<String>
    fun joinRoom(inviteCode: String): Flow<Boolean>
    fun getActiveRooms(): Flow<List<Room>>
    fun getRoomById(id: String): Flow<Room?>
    fun sendMessage(roomId: String, message: String): Flow<Boolean>
    
    fun createCollaborativePlaylist(name: String, description: String?): Flow<String>
    fun joinCollaborativePlaylist(inviteCode: String): Flow<Boolean>
    fun getCollaborativePlaylists(): Flow<List<CollaborativePlaylist>>
    fun addTrackToCollaborativePlaylist(playlistId: String, trackId: String): Flow<Boolean>
    
    getUpcomingConcerts(): Flow<List<Concert>>
    getConcertsByArtist(artistId: String): Flow<List<Concert>>
    buyConcertTicket(concertId: String): Flow<Boolean>
}
