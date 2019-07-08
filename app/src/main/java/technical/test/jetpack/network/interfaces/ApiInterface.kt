package technical.test.jetpack.network.interfaces

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import technical.test.jetpack.network.model.MarvelInfoModel

interface ApiInterface {

    @GET("characters")
    fun requestSuperHeros(
        @Query("apikey") apiKey: String?,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int): Deferred<MarvelInfoModel>
}