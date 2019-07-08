package technical.test.jetpack.di

import org.koin.dsl.module
import technical.test.jetpack.network.entities.WsRequestMarvel
import technical.test.jetpack.network.interfaces.ApiService

val networkyModule = module {
    single { WsRequestMarvel(get()) }
    single { ApiService.create() }
}