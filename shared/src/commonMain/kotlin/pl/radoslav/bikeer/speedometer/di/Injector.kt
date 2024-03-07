package pl.radoslav.bikeer.speedometer.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.radoslav.bikeer.speedometer.data.LocationRepositoryImpl
import pl.radoslav.bikeer.speedometer.domain.LocationRepository
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel


fun appModule() = listOf(platformModule, speedometerModule)

// platform Module
@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect val platformModule: Module

internal val speedometerModule = module {
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single { SpeedometerViewModel(get()) }
}