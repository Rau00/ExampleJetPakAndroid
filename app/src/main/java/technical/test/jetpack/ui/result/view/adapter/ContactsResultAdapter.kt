package technical.test.jetpack.ui.result.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import technical.test.jetpack.R
import technical.test.jetpack.data.contacts.ContactsModel

class ContactsResultAdapter(contacts: List<ContactsModel>): RecyclerView.Adapter<ContactsViewHolderResult>() {
    private var contactList: List<ContactsModel> = contacts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolderResult {
        return ContactsViewHolderResult(LayoutInflater.from(parent.context).inflate(R.layout.item_list_contact_result, parent, false))
    }

    override fun getItemCount(): Int = contactList.size


    override fun onBindViewHolder(holderResult: ContactsViewHolderResult, position: Int) {
        val viewHolderResult: ContactsViewHolderResult = holderResult
        viewHolderResult.bindView(contactList[position])
    }

    fun setContactList(contactList: List<ContactsModel>){
        this.contactList = contactList
    }
}