package com.example.profile.data.di

import com.example.core.dummy.DummyDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideDummyDataSource(): DummyDataSource {
        return DummyDataSource()
    }

}