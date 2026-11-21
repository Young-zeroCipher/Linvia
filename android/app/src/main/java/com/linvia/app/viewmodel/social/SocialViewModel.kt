package com.linvia.app.viewmodel.social

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linvia.app.model.social.CollaborativePlaylist
import com.linvia.app.model.social.Concert
import com.linvia.app.model.social.Room
import com.linvia.app.repository.social.SocialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val repository: SocialRepository
) : ViewModel() {

    private val _activeRooms = MutableStateFlow<List<Room>>(emptyList())
    val activeRooms: StateFlow<List<Room>> = _activeRooms.asStateFlow()

    private val _collaborativePlaylists = MutableStateFlow<List<CollaborativePlaylist>>(emptyList())
    val collaborativePlaylists: StateFlow<List<CollaborativePlaylist>> = _collaborativePlaylists.asStateFlow()

    private val _upcomingConcerts = MutableStateFlow<List<Concert>>(emptyList())
    val upcomingConcerts: StateFlow<List<Concert>> = _upcomingConcerts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadActiveRooms()
        loadCollaborativePlaylists()
        loadUpcomingConcerts()
    }

    private fun loadActiveRooms() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getActiveRooms().collect { rooms ->
                _activeRooms.value = rooms
                _isLoading.value = false
            }
        }
    }

    private fun loadCollaborativePlaylists() {
        viewModelScope.launch {
            repository.getCollaborativePlaylists().collect { playlists ->
                _collaborativePlaylists.value = playlists
            }
        }
    }

    private fun loadUpcomingConcerts() {
        viewModelScope.launch {
            repository.getUpcomingConcerts().collect { concerts ->
                _upcomingConcerts.value = concerts
            }
        }
    }

    fun createRoom(name: String) {
        viewModelScope.launch {
            repository.createRoom(name).collect { roomId ->
                // Navigate to room
            }
        }
    }

    fun joinRoom(inviteCode: String) {
        viewModelScope.launch {
            repository.joinRoom(inviteCode).collect { success ->
                if (success) loadActiveRooms()
            }
        }
    }

    fun createCollaborativePlaylist(name: String, description: String?) {
        viewModelScope.launch {
            repository.createCollaborativePlaylist(name, description).collect { playlistId ->
                // Navigate to playlist
            }
        }
    }

    fun buyConcertTicket(concertId: String) {
        viewModelScope.launch {
            repository.buyConcertTicket(concertId).collect { success ->
                if (success) {
                    // Show confirmation
                }
            }
        }
    }
}
