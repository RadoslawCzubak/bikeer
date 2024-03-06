package pl.radoslav.bikeer.core.location

expect class LocationManager {
    suspend fun getCurrentLocation(): GpsLocation
}