package pl.radoslav.bikeer.speedometer.presentation


sealed class SpeedometerState {
    data object Initialized : SpeedometerState()

    data class SpeedometerAvailable(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0,
        val altitude: Double = 0.0,
        val speed: Float = 0f,
    ) : SpeedometerState()

    data object SpeedometerNotAvailable : SpeedometerState()

    data class SpeedometerError(
        val message: String = ""
    ) : SpeedometerState()

    data class SpeedometerPermissionNotGranted(
        val message: String = ""
    ) : SpeedometerState()
}