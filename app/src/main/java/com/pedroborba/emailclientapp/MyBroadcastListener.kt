package com.pedroborba.emailclientapp

import com.pedroborba.model.LinkedList
import com.pedroborba.model.User

interface MyBroadcastListener {
    fun updateUser(user: User, lista: LinkedList)
}