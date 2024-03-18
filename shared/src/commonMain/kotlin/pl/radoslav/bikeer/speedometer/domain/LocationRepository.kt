package pl.radoslav.bikeer.speedometer.domain

import kotlinx.coroutines.flow.Flow
import pl.radoslav.bikeer.core.location.GpsLocation

interface LocationRepository {
    fun observeLocation(): Flow<GpsLocation>
    fun observeSpeed(): Flow<Float>
    suspend fun getCurrentLocation(): GpsLocation
}