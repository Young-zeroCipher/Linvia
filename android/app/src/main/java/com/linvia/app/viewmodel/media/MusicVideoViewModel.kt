package com.linvia.app.viewmodel.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linvia.app.model.media.MusicVideo
import com.linvia.app.repository.media.MusicVideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicVideoViewModel @Inject constructor(
    private val repository: MusicVideoRepository
) : ViewModel() {

    private val _featuredVideos = MutableStateFlow<List<MusicVideo>>(emptyList())
    val featuredVideos: StateFlow<List<MusicVideo>> = _featuredVideos.asStateFlow()

    private val _trendingVideos = MutableStateFlow<List<MusicVideo>>(emptyList())
    val trendingVideos: StateFlow<List<MusicVideo>> = _trendingVideos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFeatured()
        loadTrending()
    }

    private fun loadFeatured() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getFeaturedVideos().collect { videos ->
                _featuredVideos.value = videos
                _isLoading.value = false
            }
        }
    }

    private fun loadTrending() {
        viewModelScope.launch {
            repository.getTrendingVideos().collect { videos ->
                _trendingVideos.value = videos
            }
        }
    }
}
