package com.pedroborba.emailclientapp

import android.content.*
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedroborba.model.LinkedList
import com.pedroborba.model.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MyBroadcastListener {

    private var receiver = MyBroadcast(this)
    private var serviceIntent: Intent? = null
    private lateinit var user: User
    private lateinit var lista: LinkedList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver
        registerReceiver(receiver, IntentFilter("com.pedroborba.emailclientapp.ENVIAR_USUARIO"))

        user = User("Pedro Borba ", 29)
        lista = LinkedList()

        btnSend.setOnClickListener {
            updateUI()
        }
    }

    override fun onStart() {
        super.onStart()

        callService()
    }

    private fun callService(){
        serviceIntent = Intent()

        var bundle = Bundle()
        var lista = loadList()
        bundle.putSerializable("user", user)
        bundle.putParcelable("lista", lista)

        serviceIntent!!.putExtra("myBundle", bundle)
        serviceIntent!!.component = ComponentName("com.pedroborba.emailserviceapp","com.pedroborba.emailserviceapp.EmailService")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    fun loadList() : LinkedList{
        val list = LinkedList()
        list.push(11, "Bem Vindo ao Cesar!", "Olá, seja bem vindo ao Cesar!")
        list.push(11, "Bem Vindo ao Cesar!", "Olá, seja bem vindo ao Cesar!")
        list.push(13, "Beneficios", "Olá, conheça os beneficios...")
        list.push(17, "Novidade no Cesar", "Olá, fique por dentro...")
        list.push(17, "Novidade no Cesar", "Olá, fique por dentro...")
        list.push(22, "Integrações Cesar!", "Olá, acompanhe nossas...")
        list.push(15, "Informativo Cesar!", "Olá, no ultimo dia...")

        return list
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver);
    }

    override fun updateUser(user: User, lista: LinkedList) {
        this.user = user
        this.lista = lista
    }

    fun updateUI(){
        var temp = lista.head
        var result: String = user.name + " - " + user.idade + " "
        while (temp != null){
            result += temp.data.toString() + " - " + temp.title
            temp = temp.next
        }

        txtUser.text = result
    }


}