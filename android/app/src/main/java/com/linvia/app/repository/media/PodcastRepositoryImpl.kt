package com.linvia.app.repository.media

import com.linvia.app.model.media.Episode
import com.linvia.app.model.media.Podcast
import com.linvia.app.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PodcastRepositoryImpl(private val apiService: ApiService) : PodcastRepository {

    override fun getFeaturedPodcasts(): Flow<List<Podcast>> = flow {
        emit(emptyList())
    }

    override fun getPodcastById(id: String): Flow<Podcast?> = flow {
        emit(null)
    }

    override fun getEpisodes(podcastId: String): Flow<List<Episode>> = flow {
        emit(emptyList())
    }

    override fun searchPodcasts(query: String): Flow<List<Podcast>> = flow {
        emit(emptyList())
    }

    override fun subscribe(podcastId: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun unsubscribe(podcastId: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getSubscribedPodcasts(): Flow<List<Podcast>> = flow {
        emit(emptyList())
    }

    override fun downloadEpisode(episode: Episode): Flow<Boolean> = flow {
        emit(true)
    }

    override fun getDownloadedEpisodes(): Flow<List<Episode>> = flow {
        emit(emptyList())
    }
}
