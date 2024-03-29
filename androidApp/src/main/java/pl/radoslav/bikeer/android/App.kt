package pl.radoslav.bikeer.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.radoslav.bikeer.android.speedometer.di.androidModule
import pl.radoslav.bikeer.speedometer.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(listOf(androidModule) + appModule())
        }
    }
}