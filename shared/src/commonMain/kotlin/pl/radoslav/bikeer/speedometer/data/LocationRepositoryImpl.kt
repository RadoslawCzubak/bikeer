package pl.radoslav.bikeer.speedometer.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.radoslav.bikeer.core.location.GpsLocation
import pl.radoslav.bikeer.core.location.LocationManager
import pl.radoslav.bikeer.speedometer.domain.LocationRepository

class LocationRepositoryImpl(
    private val locationManager: LocationManager
) : LocationRepository {
    override fun observeLocation(): Flow<GpsLocation> {
        return locationManager.observeLocation()
    }

    override fun observeSpeed(): Flow<Float> = locationManager.getSpeed()

    override suspend fun getCurrentLocation(): GpsLocation {
        return locationManager.getCurrentLocation()
    }
}