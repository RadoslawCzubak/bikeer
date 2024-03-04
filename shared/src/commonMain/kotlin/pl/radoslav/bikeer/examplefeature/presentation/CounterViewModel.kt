package pl.radoslav.bikeer.examplefeature.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.update

open class CounterViewModel : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, CounterState())

    @NativeCoroutinesState
    val state = _state

    fun onEvent(counterEvent: CounterEvent) {
        when (counterEvent) {
            CounterEvent.Decrement -> _state.update { state ->
                state.copy(counter = state.counter - 1)
            }

            CounterEvent.Increment -> _state.update { state ->
                state.copy(counter = state.counter + 1)
            }
        }
    }
}