package com.pedroborba.emailclientapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pedroborba.model.LinkedList
import com.pedroborba.model.User

class MyBroadcast : BroadcastReceiver {

    var user : User = User()
    var lista: LinkedList = LinkedList()
    lateinit var listener : MyBroadcastListener

    constructor(listener: MyBroadcastListener){
        this.listener = listener
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        var bundle = intent?.getParcelableExtra("myBundle") as Bundle
        user = bundle.getParcelable<User>("user") as User
        var lista = bundle.getParcelable<LinkedList>("lista") as LinkedList
        println("@@@@@@@@@@@@ USER: ${user.name} IDADE ${user.idade}")

        listener.updateUser(user, lista)
    }

}