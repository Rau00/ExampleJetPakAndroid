package technical.test.jetpack.network.repository

import androidx.lifecycle.MutableLiveData
import org.junit.Test

import org.junit.Assert.*
import technical.test.jetpack.data.contacts.ContactsModel

class SuperHerosRepositoryImplTest: SuperHerosRepository {


    override val superHeroList = MutableLiveData<MutableList<ContactsModel>>()

    override suspend fun fetchSuperHeros() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun getSuperHeroList() {
    }
}