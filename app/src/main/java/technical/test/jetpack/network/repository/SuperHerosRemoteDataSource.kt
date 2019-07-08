package technical.test.jetpack.network.repository

import technical.test.jetpack.network.entities.WsRequestMarvel
import technical.test.jetpack.network.interfaces.RemoteDataNotFoundException
import technical.test.jetpack.network.model.Character
import technical.test.jetpack.network.interfaces.HerosDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SuperHerosRemoteDataSource
constructor(val service: WsRequestMarvel) :
    HerosDataSource {

    override suspend fun getSuperHeroList(): Result<List<Character>> = withContext(Dispatchers.IO) {
        val request = service.requestSuperHeros()
        try {
            val response = request.await()
            if (response.data.characters.isNotEmpty()) {
                Result.Success(response.data.characters)
            } else {
                Result.Error(RemoteDataNotFoundException())
            }
        } catch (e: HttpException) {
            Result.Error(RemoteDataNotFoundException())
        } catch (e: Throwable) {
            Result.Error(RemoteDataNotFoundException())
        }
    }
}