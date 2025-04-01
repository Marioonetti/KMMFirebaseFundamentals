package org.marioonetti.firebasefundamentals.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import org.koin.core.component.KoinComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterEffect


abstract class RootViewModel<S : ViewState, E : ViewEvent, C: ViewEffect>(
    initialState: S,
) : KoinComponent {
    private val vmJob = SupervisorJob()
    protected val vmScope: CoroutineScope get() = CoroutineScope(vmJob + Dispatchers.Main)

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<C>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    abstract fun onEvent(event: E)

    open fun clearScope() {
        vmJob.cancel()
    }

    protected fun updateState(reducer: S.() -> S) {
        _uiState.value = _uiState.value.reducer()
    }

    protected suspend fun setEffect(effect: C) {
        _sideEffect.send(effect)
    }
}

open class ViewState
open class ViewEvent
open class ViewEffect
