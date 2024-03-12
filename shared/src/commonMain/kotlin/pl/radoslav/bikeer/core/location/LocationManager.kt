package pl.radoslav.bikeer.core.location

import kotlinx.coroutines.flow.Flow

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class LocationManager {
    suspend fun getCurrentLocation(): GpsLocation
    fun getSpeed(): Flow<Float>
}