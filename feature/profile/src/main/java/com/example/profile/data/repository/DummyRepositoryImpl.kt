package com.example.profile.data.repository

import com.example.core.dummy.DummyDataSource
import com.example.profile.domain.repository.DummyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {
    override suspend fun populateData() {
        dummyDataSource.populateAll()
    }

    override suspend fun getAllData(): Flow<List<Int>> {
        return dummyDataSource.getAllData()
    }

}