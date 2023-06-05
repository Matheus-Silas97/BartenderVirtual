package com.example.letsdrink.common.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface Event<T> {
    val event: Flow<T>
    fun ViewModel.sendEvent(event: T): Job
}

class EventImpl<T> : Event<T> {
    private val _event = Channel<T>(Channel.BUFFERED)
    override val event = _event.receiveAsFlow()

    override fun ViewModel.sendEvent(event: T): Job = viewModelScope.launch {
        _event.send(element = event)
    }

}