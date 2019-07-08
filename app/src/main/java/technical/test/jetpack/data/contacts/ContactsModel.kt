package technical.test.jetpack.data.contacts

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class ContactsModel(val id: Int = 0,
                         val name: String,
                         val thumbnail: String = "",
                         val contactThumbnail: Uri? = null,
                         var isSelected: Boolean = false,
                         var amountToPay: String = "0") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(thumbnail)
        parcel.writeParcelable(contactThumbnail, flags)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeString(amountToPay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactsModel> {
        override fun createFromParcel(parcel: Parcel): ContactsModel {
            return ContactsModel(parcel)
        }

        override fun newArray(size: Int): Array<ContactsModel?> {
            return arrayOfNulls(size)
        }
    }

}
