package pl.radoslav.bikeer.core.location

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class LocationManager {
    suspend fun getCurrentLocation(): GpsLocation
}