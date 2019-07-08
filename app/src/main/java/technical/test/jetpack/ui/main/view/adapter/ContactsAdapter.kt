package technical.test.jetpack.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import technical.test.jetpack.R
import technical.test.jetpack.data.contacts.ContactsModel

class ContactsAdapter(contacts: List<ContactsModel>, var listener: (ContactsViewHolder) -> Unit): RecyclerView.Adapter<ContactsViewHolder>() {
    private var contactList: List<ContactsModel> = contacts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_contact_selector, parent, false))
    }

    override fun getItemCount(): Int = contactList.size


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val viewHolder: ContactsViewHolder = holder
        viewHolder.bindView(contactList[position], listener = this.listener)
    }

    fun setContactList(contactList: List<ContactsModel>){
        this.contactList = contactList
    }
}