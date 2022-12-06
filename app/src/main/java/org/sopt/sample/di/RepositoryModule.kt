package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.api.ApiClient
import org.sopt.sample.data.auth.repository.AuthRepository
import org.sopt.sample.data.auth.service.AuthService
import org.sopt.sample.data.auth.source.AuthDataSourceImpl
import org.sopt.sample.data.home.repository.HomeRepository
import org.sopt.sample.data.home.service.HomeService
import org.sopt.sample.data.home.source.HomeDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepository(
            HomeDataSourceImpl(
                ApiClient.getRetrofitForUserList()!!.create(HomeService::class.java)
            )
        )
    }

    @Singleton
    @Provides
    fun provideSignRepository(): AuthRepository {
        return AuthRepository(
            AuthDataSourceImpl(
                ApiClient.getRetrofitForAuth()!!.create(AuthService::class.java)
            )
        )
    }
}