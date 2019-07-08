package technical.test.jetpack

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import technical.test.jetpack.di.*


class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@MarvelApplication)
            // declare modules
            modules(listOf(repositoryModule, viewModelModule, networkyModule, routerModule,
                viewyModule))
        }

    }

}