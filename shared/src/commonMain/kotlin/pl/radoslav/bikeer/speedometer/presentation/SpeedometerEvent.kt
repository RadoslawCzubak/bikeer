package pl.radoslav.bikeer.speedometer.presentation

sealed class SpeedometerEvent {
    data object CheckLocation : SpeedometerEvent()
}