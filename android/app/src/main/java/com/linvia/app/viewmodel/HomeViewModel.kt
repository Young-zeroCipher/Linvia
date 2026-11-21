package com.linvia.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linvia.app.model.Track
import com.linvia.app.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.StateFlowImpl
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {

    val featuredTracks: StateFlow<List<Track>> = StateFlowImpl(emptyList())
    val isLoading = StateFlowImpl(false)

    init {
        loadFeatured()
    }

    private fun loadFeatured() {
        viewModelScope.launch {
            repository.getFeaturedTracks().collect { tracks ->
                // In a real app, update state flow
            }
        }
    }
}
