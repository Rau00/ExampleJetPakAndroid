package technical.test.jetpack.di

import org.koin.dsl.module
import technical.test.jetpack.network.interfaces.HerosDataSource
import technical.test.jetpack.network.repository.SuperHerosRemoteDataSource
import technical.test.jetpack.network.repository.SuperHerosRepository
import technical.test.jetpack.network.repository.SuperHerosRepositoryImpl

val repositoryModule = module {
    single { SuperHerosRepositoryImpl(get()) as SuperHerosRepository }
    factory { SuperHerosRemoteDataSource(get()) as HerosDataSource}
}