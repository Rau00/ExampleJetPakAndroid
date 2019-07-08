package technical.test.jetpack.ui.result.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import org.koin.android.ext.android.inject
import technical.test.jetpack.data.contacts.ContactsModel
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.main.router.MainRouter
import technical.test.jetpack.ui.result.router.ResultRouter
import technical.test.jetpack.ui.result.view.ResultView
import technical.test.jetpack.utils.Constants
import technical.test.jetpack.utils.formatter.AmountFormatter

class ResultViewModelImpl(private val resultView: ResultView ) : ViewModel(), ResultViewModel {
    val amount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val contactList: MutableLiveData<MutableList<ContactsModel>> by lazy {
        MutableLiveData<MutableList<ContactsModel>>() }

    private val router: ResultRouter by (resultView as Fragment).inject()

    fun init(resultView: ResultView) {
        router.setNavigationView((resultView as Fragment).activity as NavigationView)
        val amount: String = (resultView as Fragment)
            .activity?.intent?.extras?.getBundle(Constants.AMOUNT)?.getString(Constants.AMOUNT).toString()

        val contactsList: ArrayList<ContactsModel> = (resultView as Fragment)
            .activity?.intent?.extras?.getBundle(Constants.CONTACTS_LIST)?.
            getParcelableArrayList<ContactsModel>(Constants.CONTACTS_LIST) as ArrayList<ContactsModel>

        calculateAmountForEach(amount, contactsList)
        this.contactList.value = contactsList
        this.amount.value = amount
    }

    private fun calculateAmountForEach(amount: String, contacts: ArrayList<ContactsModel>) {
        val amountNumber = AmountFormatter.prepareAmountToProcess(amount).toFloat()
        val amountForEach = AmountFormatter.formatAmount((amountNumber / contacts.size).toString())
        for (contact  in contacts) {
            contact.amountToPay = amountForEach
        }
    }

    override fun backStep() {
        router.backStep()
    }
}
