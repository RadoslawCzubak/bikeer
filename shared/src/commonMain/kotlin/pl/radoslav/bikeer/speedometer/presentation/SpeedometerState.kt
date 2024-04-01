package pl.radoslav.bikeer.speedometer.presentation


sealed class SpeedometerState {
    data class SpeedometerAvailable(
        val latitude: Double = 1.0,
        val longitude: Double = 1.0,
        val altitude: Double = 1.0,
        val speed: Float = 0f,
    ): SpeedometerState()

    data object SpeedometerNotAvailable : SpeedometerState()

    data class SpeedometerError(
        val message: String = ""
    ) : SpeedometerState()

    data class SpeedometerPermissionNotGranted(
        val message: String = ""
    ) : SpeedometerState()
}