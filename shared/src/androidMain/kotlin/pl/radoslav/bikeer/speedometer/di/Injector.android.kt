package pl.radoslav.bikeer.speedometer.di

import org.koin.dsl.module
import pl.radoslav.bikeer.core.location.LocationManager

actual val platformModule = module {
    single<LocationManager> {
        LocationManager(get())
    }
}