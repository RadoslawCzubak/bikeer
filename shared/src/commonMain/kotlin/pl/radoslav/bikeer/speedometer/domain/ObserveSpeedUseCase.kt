package pl.radoslav.bikeer.speedometer.domain

import kotlinx.coroutines.flow.Flow

class ObserveSpeedUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Flow<Float> =
        locationRepository.observeSpeed()
}