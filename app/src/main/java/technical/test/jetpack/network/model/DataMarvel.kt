package technical.test.jetpack.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataMarvel(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    @Json(name = "results") val characters: List<Character>
)