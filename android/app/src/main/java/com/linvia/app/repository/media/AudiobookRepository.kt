package com.linvia.app.repository.media

import com.linvia.app.model.media.Audiobook
import kotlinx.coroutines.flow.Flow

interface AudiobookRepository {
    fun getFeaturedAudiobooks(): Flow<List<Audiobook>>
    fun getAudiobookById(id: String): Flow<Audiobook?>
    fun searchAudiobooks(query: String): Flow<List<Audiobook>>
    fun purchase(audiobookId: String): Flow<Boolean>
    fun getPurchasedAudiobooks(): Flow<List<Audiobook>>
    fun getTopCharts(): Flow<List<Audiobook>>
}
