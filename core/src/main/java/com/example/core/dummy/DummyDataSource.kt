package com.example.core.dummy

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyDataSource {
    private val data = mutableListOf<Int>()

    fun populateAll(){
        repeat(10) {
            data.add(it)
        }
    }

    fun getAllData() : Flow<List<Int>> {
        return flow {
            emit(data)
        }
    }
}