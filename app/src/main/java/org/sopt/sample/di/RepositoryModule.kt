package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.api.ApiClient
import org.sopt.sample.data.api.AuthService
import org.sopt.sample.data.api.HomeService
import org.sopt.sample.data.api.MusicService
import org.sopt.sample.data.repository.AuthRepositoryImpl
import org.sopt.sample.data.repository.HomeRepositoryImpl
import org.sopt.sample.data.repository.MusicRepositoryImpl
import org.sopt.sample.data.source.remote.AuthDataSource
import org.sopt.sample.data.source.remote.HomeDataSource
import org.sopt.sample.data.source.remote.MusicDataSource
import org.sopt.sample.domain.AuthRepository
import org.sopt.sample.domain.HomeRepository
import org.sopt.sample.domain.MusicRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepositoryImpl(
            HomeDataSource(
                ApiClient.getRetrofitForUserList()!!.create(HomeService::class.java)
            )
        )
    }

    @Singleton
    @Provides
    fun provideSignRepository(): AuthRepository {
        return AuthRepositoryImpl(
            AuthDataSource(
                ApiClient.getRetrofitForAuth()!!.create(AuthService::class.java)
            )
        )
    }

    @Singleton
    @Provides
    fun provideMusicRepository(): MusicRepository {
        return MusicRepositoryImpl(
            MusicDataSource(
                ApiClient.getRetrofitForMusicList()!!.create(MusicService::class.java)
            )
        )
    }
}