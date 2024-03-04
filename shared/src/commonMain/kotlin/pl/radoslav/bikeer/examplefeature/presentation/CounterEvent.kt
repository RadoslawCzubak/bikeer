package pl.radoslav.bikeer.examplefeature.presentation

sealed interface CounterEvent {
    data object Increment : CounterEvent
    data object Decrement : CounterEvent
}