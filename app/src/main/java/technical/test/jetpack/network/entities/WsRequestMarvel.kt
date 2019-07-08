package technical.test.jetpack.network.entities

import technical.test.jetpack.network.interfaces.ApiInterface
import kotlinx.coroutines.Deferred
import technical.test.jetpack.network.model.MarvelInfoModel
import technical.test.jetpack.utils.encryption.EncryptionUtils

class WsRequestMarvel
constructor(val apiInterface: ApiInterface) {

    private val apikeyPublic = "0e469aac93ed81b266b2d4cd94288006"
    private val apikeyPrivate = "a7625563d4fcb27e849b584104e554e49fde8d87"

    suspend fun requestSuperHeros(): Deferred<MarvelInfoModel> {
        val timeStamp = EncryptionUtils.generateTimeStamp()
        val hash = EncryptionUtils.hashString(timeStamp + apikeyPrivate + apikeyPublic)
        return apiInterface.requestSuperHeros(apiKey = apikeyPublic, timeStamp = timeStamp, hash = hash, limit = 50)
    }
}