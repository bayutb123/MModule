package com.example.profile.domain.di

import com.example.core.dummy.DummyDataSource
import com.example.profile.data.repository.DummyRepositoryImpl
import com.example.profile.domain.repository.DummyRepository
import com.example.profile.domain.usecase.DummyUseCase
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
    fun provideProfileRepository(dummyDataSource: DummyDataSource) : DummyRepository {
        return DummyRepositoryImpl(dummyDataSource)
    }

    @Provides
    @Singleton
    fun provideProfileUseCase(dummyRepository: DummyRepository) : DummyUseCase {
        return DummyUseCase(dummyRepository)
    }
}