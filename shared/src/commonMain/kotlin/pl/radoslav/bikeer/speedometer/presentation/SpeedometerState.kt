package pl.radoslav.bikeer.speedometer.presentation

data class SpeedometerState(
    val latitude: Double = 1.0,
    val longitude: Double = 1.0,
    val altitude: Double = 1.0,
    val speed: Float = 0f,
)
