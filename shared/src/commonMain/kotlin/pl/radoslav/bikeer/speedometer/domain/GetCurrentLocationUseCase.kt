package pl.radoslav.bikeer.speedometer.domain

import pl.radoslav.bikeer.core.location.GpsLocation

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(): Result<GpsLocation> = runCatching {
        locationRepository.getCurrentLocation()
    }
}