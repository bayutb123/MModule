package com.example.profile.domain.usecase

import com.example.profile.domain.repository.DummyRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DummyUseCase @Inject constructor(
    private val repository: DummyRepository,
) {
    suspend operator fun invoke() = flow {
        emitAll(repository.getAllData().map { intList ->
            intList.sortedByDescending { it }
        })
    }
}