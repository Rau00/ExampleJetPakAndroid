package technical.test.jetpack.network.repository

import androidx.lifecycle.MutableLiveData
import technical.test.jetpack.network.interfaces.RemoteDataNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import technical.test.jetpack.data.contacts.ContactsModel
import technical.test.jetpack.network.interfaces.HerosDataSource
import technical.test.jetpack.network.model.Character
import technical.test.jetpack.utils.Constants
import kotlin.collections.ArrayList

class SuperHerosRepositoryImpl
    constructor(val userRemoteDataSource: HerosDataSource): SuperHerosRepository {

    override val superHeroList = MutableLiveData<MutableList<ContactsModel>>()

    override suspend fun fetchSuperHeros() {
        withContext(Dispatchers.IO) {
            try {
                val result = userRemoteDataSource.getSuperHeroList()
                if (result is Result.Success) {
                    val contactList: MutableList<ContactsModel> = ArrayList()
                    for (character: Character in result.data){
                        var contactsModel = ContactsModel(id = character.id, name = character.name,
                            thumbnail = character.thumbnail.path + Constants.POINT + character.thumbnail.extension)
                        contactList.add(contactsModel)
                    }
                    superHeroList.postValue(contactList)
                }
            } catch (error: RemoteDataNotFoundException) {

            }
        }
    }
}