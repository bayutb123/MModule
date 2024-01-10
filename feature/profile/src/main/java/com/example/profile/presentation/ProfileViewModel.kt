package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.domain.repository.DummyRepository
import com.example.profile.domain.usecase.DummyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase,
    private val repository: DummyRepository
) : ViewModel() {
    private val channel = Channel<Event>()
    val event = channel.receiveAsFlow()

    init {
        checkData()
    }

    private fun checkData() {
        viewModelScope.launch {
            repository.getAllData().collect() {
                if (it.isNotEmpty()) {
                    channel.send(Event.OnDataPopulated(it))
                } else {
                    channel.send(Event.Empty)
                }
            }
        }
    }

    fun populateData() {
        viewModelScope.launch {
            channel.send(Event.Loading)
            delay(1000)
            repository.populateData()
            channel.send(Event.OnPopulateData)
        }
    }

    fun onDataPopulated() {
        viewModelScope.launch {
            dummyUseCase().collect { data ->
                channel.send(Event.OnDataObtained(data))
            }
        }
    }

    sealed class Event {
        data object Loading : Event()
        data class OnDataObtained(val data: List<Int>) : Event()
        data object OnPopulateData : Event()
        data class OnDataPopulated(val data: List<Int>) : Event()
        data object Empty : Event()
    }

}