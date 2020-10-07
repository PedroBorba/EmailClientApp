package com.pedroborba.emailclientapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pedroborba.model.LinkedList

class MyBroadcast : BroadcastReceiver {

    var lista: LinkedList = LinkedList()
    var listener : MyBroadcastListener

    constructor(listener: MyBroadcastListener){
        this.listener = listener
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        var bundle = intent?.getParcelableExtra("myBundle") as Bundle
        lista = bundle.getParcelable<LinkedList>("lista") as LinkedList

        listener.clearDuplicates(lista)
    }

}