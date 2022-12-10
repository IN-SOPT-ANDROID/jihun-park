package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.application.ApplicationClass
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object ApplicationModule {
    @Singleton
    @Provides
    fun provideApplication(): ApplicationClass {
        return ApplicationClass.getInstance()
    }
}