package technical.test.jetpack.network.interfaces

import technical.test.jetpack.network.model.Character
import technical.test.jetpack.network.repository.Result

interface HerosDataSource {
    suspend fun getSuperHeroList(): Result<List<Character>>
}