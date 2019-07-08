package technical.test.jetpack.ui.result.view.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_contact_common.view.*
import kotlinx.android.synthetic.main.item_list_contact_result.view.*
import technical.test.jetpack.data.contacts.ContactsModel

class ContactsViewHolderResult(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var contact: ContactsModel

    fun bindView(contact: ContactsModel) {
        this.contact = contact
        itemView.tvName.text = contact.name
        itemView.tvResumeImport.text = contact.amountToPay

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
}