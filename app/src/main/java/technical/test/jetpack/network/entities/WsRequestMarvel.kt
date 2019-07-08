package technical.test.jetpack.network.entities

import technical.test.jetpack.network.interfaces.ApiInterface
import kotlinx.coroutines.Deferred
import technical.test.jetpack.network.model.MarvelInfoModel
import technical.test.jetpack.utils.encryption.EncryptionUtils

class WsRequestMarvel
constructor(val apiInterface: ApiInterface) {

    private val apikeyPublic = "990025945ddf8c5fbe3e1c4fa9c659da"
    private val apikeyPrivate = "773c348e22e1e1bd10e9e65665fbeb9d85d61895"

    suspend fun requestSuperHeros(): Deferred<MarvelInfoModel> {
        val timeStamp = EncryptionUtils.generateTimeStamp()
        val hash = EncryptionUtils.hashString(timeStamp + apikeyPrivate + apikeyPublic)
        return apiInterface.requestSuperHeros(apiKey = apikeyPublic, timeStamp = timeStamp, hash = hash, limit = 50)
    }
}