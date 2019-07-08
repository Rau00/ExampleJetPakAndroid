package technical.test.jetpack.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import technical.test.jetpack.data.contacts.ContactsModel
import technical.test.jetpack.ui.main.view.MainView
import technical.test.jetpack.ui.main.viewmodel.MainViewModel

interface MainViewModel {
    fun getSuperHeros(): MutableLiveData<MutableList<ContactsModel>>
    fun itemClicked(contactsModel: ContactsModel)
    fun initView(mainView: MainView)
    fun nextStep()
}