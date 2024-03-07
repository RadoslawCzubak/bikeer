package pl.radoslav.bikeer.speedometer.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import pl.radoslav.bikeer.core.location.LocationManager
import pl.radoslav.bikeer.speedometer.domain.LocationRepository

// TODO: Prepare app to use Koin - Crash on iOS atm
fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

class LocationRepositoryHelper : KoinComponent {
    val locationRepository: LocationRepository by inject()
}

fun getLocationManager() = LocationManager()
