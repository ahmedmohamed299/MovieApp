package banquemisr.challenge05.movieApp.presentation

import banquemisr.challenge05.movieApp.di.networkModule
import banquemisr.challenge05.movieApp.di.repositoryModule
import banquemisr.challenge05.movieApp.di.useCaseModule
import banquemisr.challenge05.movieApp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class App: android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}