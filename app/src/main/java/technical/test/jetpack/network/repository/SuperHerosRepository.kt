package technical.test.jetpack.network.repository

import androidx.lifecycle.MutableLiveData
import technical.test.jetpack.data.contacts.ContactsModel

interface SuperHerosRepository {

    val superHeroList: MutableLiveData<MutableList<ContactsModel>>

    suspend fun fetchSuperHeros()

}