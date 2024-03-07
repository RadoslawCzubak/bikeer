package pl.radoslav.bikeer.android.speedometer.di

import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pl.radoslav.bikeer.core.location.LocationManager
import pl.radoslav.bikeer.speedometer.data.LocationRepositoryImpl
import pl.radoslav.bikeer.speedometer.domain.LocationRepository
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel

val androidModule = module {
    viewModelOf(::SpeedometerViewModel)
    single { LocationManager(fuseLocationManager = get()) }
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
}