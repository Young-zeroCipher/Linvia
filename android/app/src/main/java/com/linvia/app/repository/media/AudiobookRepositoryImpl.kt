package com.linvia.app.repository.media

import com.linvia.app.model.media.Audiobook
import com.linvia.app.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AudiobookRepositoryImpl(private val apiService: ApiService) : AudiobookRepository {

    override fun getFeaturedAudiobooks(): Flow<List<Audiobook>> = flow {
        emit(emptyList())
    }

    override fun getAudiobookById(id: String): Flow<Audiobook?> = flow {
        emit(null)
    }

    override fun searchAudiobooks(query: String): Flow<List<Audiobook>> = flow {
        emit(emptyList())
    }

    override fun purchase(audiobookId: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getPurchasedAudiobooks(): Flow<List<Audiobook>> = flow {
        emit(emptyList())
    }

    override fun getTopCharts(): Flow<List<Audiobook>> = flow {
        emit(emptyList())
    }
}
