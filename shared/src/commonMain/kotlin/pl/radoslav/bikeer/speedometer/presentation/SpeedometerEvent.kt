package pl.radoslav.bikeer.speedometer.presentation

sealed class SpeedometerEvent {
    data object ObserveLocation : SpeedometerEvent()
    data object OnNoLocationPermission : SpeedometerEvent()
}