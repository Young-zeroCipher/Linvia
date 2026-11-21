package com.linvia.app.di

import com.linvia.app.repository.MusicRepository
import com.linvia.app.repository.MusicRepositoryImpl
import com.linvia.app.repository.media.AudiobookRepository
import com.linvia.app.repository.media.AudiobookRepositoryImpl
import com.linvia.app.repository.media.MusicVideoRepository
import com.linvia.app.repository.media.MusicVideoRepositoryImpl
import com.linvia.app.repository.media.PodcastRepository
import com.linvia.app.repository.media.PodcastRepositoryImpl
import com.linvia.app.repository.social.SocialRepository
import com.linvia.app.repository.social.SocialRepositoryImpl
import com.linvia.app.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.linvia.app/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMusicRepository(apiService: ApiService): MusicRepository {
        return MusicRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePodcastRepository(apiService: ApiService): PodcastRepository {
        return PodcastRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAudiobookRepository(apiService: ApiService): AudiobookRepository {
        return AudiobookRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMusicVideoRepository(apiService: ApiService): MusicVideoRepository {
        return MusicVideoRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSocialRepository(apiService: ApiService): SocialRepository {
        return SocialRepositoryImpl(apiService)
    }
}
