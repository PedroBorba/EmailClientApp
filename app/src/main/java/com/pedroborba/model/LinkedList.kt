package com.pedroborba.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class LinkedList (var head : Node? = null) : Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
        head = parcel.readParcelable(Node::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(head, flags)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<LinkedList>{
            override fun createFromParcel(parcel: Parcel) = LinkedList(parcel)

            override fun newArray(size: Int) = arrayOfNulls<LinkedList>(size)
        }
    }

    /* Linked list Node*/
    class Node(var data: Int = 0, var title: String? = "", var message: String? = "", var next: Node? = null) : Parcelable, Serializable {


        constructor(parcel: Parcel) : this(
            data = parcel.readInt(),
            title = parcel.readString(),
            message = parcel.readString(),
            next = parcel.readParcelable(Node::class.java.classLoader)
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(data)
            parcel.writeString(title)
            parcel.writeString(message)
            parcel.writeParcelable(next, flags)
        }

        override fun describeContents() = 0

        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<Node>{
                override fun createFromParcel(parcel: Parcel) = Node(parcel)

                override fun newArray(size: Int) = arrayOfNulls<Node>(size)
            }
        }
    }

    fun push(new_data: Int, title: String, message: String) {
        val node = Node(new_data, title, message)
        node.next = null
        if (head == null) {
            head = node
        } else {
            var n: Node? = head
            while (n?.next != null) {
                n = n.next
            }
            n?.next = node
        }
    }
}