package com.linvia.app.viewmodel.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linvia.app.model.media.Episode
import com.linvia.app.model.media.Podcast
import com.linvia.app.repository.media.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val repository: PodcastRepository
) : ViewModel() {

    private val _featuredPodcasts = MutableStateFlow<List<Podcast>>(emptyList())
    val featuredPodcasts: StateFlow<List<Podcast>> = _featuredPodcasts.asStateFlow()

    private val _subscribedPodcasts = MutableStateFlow<List<Podcast>>(emptyList())
    val subscribedPodcasts: StateFlow<List<Podcast>> = _subscribedPodcasts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFeatured()
        loadSubscribed()
    }

    private fun loadFeatured() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getFeaturedPodcasts().collect { podcasts ->
                _featuredPodcasts.value = podcasts
                _isLoading.value = false
            }
        }
    }

    private fun loadSubscribed() {
        viewModelScope.launch {
            repository.getSubscribedPodcasts().collect { podcasts ->
                _subscribedPodcasts.value = podcasts
            }
        }
    }

    fun subscribe(podcastId: String) {
        viewModelScope.launch {
            repository.subscribe(podcastId).collect { success ->
                if (success) loadSubscribed()
            }
        }
    }

    fun unsubscribe(podcastId: String) {
        viewModelScope.launch {
            repository.unsubscribe(podcastId).collect { success ->
                if (success) loadSubscribed()
            }
        }
    }
}
