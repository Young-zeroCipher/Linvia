package com.linvia.app.viewmodel.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linvia.app.model.media.Audiobook
import com.linvia.app.repository.media.AudiobookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudiobookViewModel @Inject constructor(
    private val repository: AudiobookRepository
) : ViewModel() {

    private val _featuredAudiobooks = MutableStateFlow<List<Audiobook>>(emptyList())
    val featuredAudiobooks: StateFlow<List<Audiobook>> = _featuredAudiobooks.asStateFlow()

    private val _purchasedAudiobooks = MutableStateFlow<List<Audiobook>>(emptyList())
    val purchasedAudiobooks: StateFlow<List<Audiobook>> = _purchasedAudiobooks.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFeatured()
        loadPurchased()
    }

    private fun loadFeatured() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getFeaturedAudiobooks().collect { audiobooks ->
                _featuredAudiobooks.value = audiobooks
                _isLoading.value = false
            }
        }
    }

    private fun loadPurchased() {
        viewModelScope.launch {
            repository.getPurchasedAudiobooks().collect { audiobooks ->
                _purchasedAudiobooks.value = audiobooks
            }
        }
    }

    fun purchase(audiobookId: String) {
        viewModelScope.launch {
            repository.purchase(audiobookId).collect { success ->
                if (success) loadPurchased()
            }
        }
    }
}
