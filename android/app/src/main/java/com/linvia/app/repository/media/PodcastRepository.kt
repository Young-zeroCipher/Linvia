package com.linvia.app.repository.media

import com.linvia.app.model.media.Episode
import com.linvia.app.model.media.Podcast
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {
    fun getFeaturedPodcasts(): Flow<List<Podcast>>
    fun getPodcastById(id: String): Flow<Podcast?>
    fun getEpisodes(podcastId: String): Flow<List<Episode>>
    fun searchPodcasts(query: String): Flow<List<Podcast>>
    fun subscribe(podcastId: String): Flow<Boolean>
    fun unsubscribe(podcastId: String): Flow<Boolean>
    fun getSubscribedPodcasts(): Flow<List<Podcast>>
    fun downloadEpisode(episode: Episode): Flow<Boolean>
    fun getDownloadedEpisodes(): Flow<List<Episode>>
}
