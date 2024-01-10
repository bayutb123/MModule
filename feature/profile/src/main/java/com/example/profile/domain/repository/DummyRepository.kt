package com.example.profile.domain.repository

import kotlinx.coroutines.flow.Flow

interface DummyRepository {
    suspend fun populateData()
    suspend fun getAllData() : Flow<List<Int>>
}