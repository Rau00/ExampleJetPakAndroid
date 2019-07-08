package technical.test.jetpack.ui.main.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import technical.test.jetpack.data.contacts.ContactsModel
import technical.test.jetpack.network.interfaces.RemoteDataNotFoundException
import technical.test.jetpack.network.repository.SuperHerosRepository
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.main.router.MainRouter
import technical.test.jetpack.ui.main.view.MainView
import technical.test.jetpack.utils.Constants

class MainViewModelImpl (private val mainView: MainView,
                         private val superHeroRepository: SuperHerosRepository
) : MainViewModel, ViewModel() {

    private val contactList = superHeroRepository.superHeroList

    private val router: MainRouter by (mainView as Fragment).inject()

    private val contactsSelected = ArrayList<ContactsModel>()

    init {
        fetchUser()
    }

    override fun initView(mainView: MainView) {
        router.setNavigationView((mainView as Fragment).activity as NavigationView)
    }

    override fun getSuperHeros(): MutableLiveData<MutableList<ContactsModel>> {
        return contactList
    }

    override fun itemClicked(contactsModel: ContactsModel) {
        when (contactsModel.isSelected) {
            true -> {
                contactsModel.isSelected = false
                contactsSelected.remove(contactsModel)
            }
            false -> {
                contactsModel.isSelected = true
                contactsSelected.add(contactsModel)
            }
        }
    }

    override fun nextStep() {
        val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.CONTACTS_LIST, contactsSelected)
        router.setBundleInfo(Constants.CONTACTS_LIST, bundle)
        router.nextScreen()
    }

    private fun fetchUser() {
        loadData { superHeroRepository.fetchSuperHeros() }
    }

    private fun loadData(block: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (error: RemoteDataNotFoundException) {
                //TODO open error screen
            }
        }
    }

}
