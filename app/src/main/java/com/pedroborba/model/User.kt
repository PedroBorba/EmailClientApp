package com.pedroborba.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class User(
    var name: String? = "",
    var idade: Int = 0
) : Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        idade = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(idade)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<User>{
            override fun createFromParcel(parcel: Parcel) = User(parcel)

            override fun newArray(size: Int) = arrayOfNulls<User>(size)
        }
    }

}