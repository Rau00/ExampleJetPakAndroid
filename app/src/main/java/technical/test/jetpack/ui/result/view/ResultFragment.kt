package technical.test.jetpack.ui.result.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.result_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

import technical.test.jetpack.R
import technical.test.jetpack.ui.interfacebase.CollapsableHeaderConfiguration
import technical.test.jetpack.ui.result.view.adapter.ContactsResultAdapter
import technical.test.jetpack.ui.result.viewmodel.ResultViewModelImpl
import technical.test.jetpack.utils.Constants
import java.util.Arrays.asList

class ResultFragment : Fragment(), ResultView {
    companion object {

        fun newInstance() = ResultFragment()

    }
    private lateinit var viewModel: ResultViewModelImpl

    private var contactsAdapter: ContactsResultAdapter = ContactsResultAdapter(asList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        fillViews()
        drawAmount()
        drawContactList()
        setupListener()
        viewModel.init(this)
    }

    override fun fillViews() {
        contactListResult.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = contactsAdapter
        }
    }

    override fun drawAmount() {
        viewModel.amount.observe(viewLifecycleOwner, Observer {
            if(activity is CollapsableHeaderConfiguration) {
                (activity as CollapsableHeaderConfiguration).changeText(
                    String.format(getString(R.string.amount_input_info), it))
            }
        })
    }

    override fun drawContactList() {
        viewModel.contactList.observe(viewLifecycleOwner, Observer { contactList ->
            contactsAdapter.setContactList(contactList)
            contactsAdapter.notifyDataSetChanged()
        })
    }

    override fun setupListener() {
        resultNavigationComponent.actionBack { viewModel.backStep() }
    }
}
