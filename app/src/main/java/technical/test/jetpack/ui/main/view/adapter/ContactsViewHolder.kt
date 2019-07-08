package technical.test.jetpack.ui.main.view.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_contact_common.view.*
import kotlinx.android.synthetic.main.item_list_contact_selector.view.*
import technical.test.jetpack.data.contacts.ContactsModel

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var contact: ContactsModel

    fun bindView(contact: ContactsModel, listener: (ContactsViewHolder) -> Unit) {
        this.contact = contact
        itemView.setOnClickListener { listener(this) }
        itemView.cbcontactSelected.setOnClickListener { listener(this) }
        itemView.tvName.text = contact.name
        itemView.cbcontactSelected.isChecked = contact.isSelected

        if (!contact.thumbnail.isEmpty()) {
            Picasso.get()
                .load(contact.thumbnail)
                .placeholder(android.R.drawable.alert_light_frame)
                .error(android.R.drawable.stat_notify_error)
                .fit()
                .into(itemView.ivThumbnail)
        } else {
            itemView.ivThumbnail.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    android.R.drawable.ic_menu_gallery))
        }
    }

    fun updateSelection() {
        itemView.cbcontactSelected.isChecked = contact.isSelected
    }

    fun getContactModel():ContactsModel {return contact}
}