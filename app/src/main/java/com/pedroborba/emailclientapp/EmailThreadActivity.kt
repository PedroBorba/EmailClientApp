package com.pedroborba.emailclientapp

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedroborba.adapter.EmailAdapter
import com.pedroborba.model.LinkedList
import com.pedroborba.model.LinkedList.Node
import kotlinx.android.synthetic.main.activity_email_thread.*

class EmailThreadActivity : AppCompatActivity(), MyBroadcastListener {

    private var receiver = MyBroadcast(this)
    private var serviceIntent: Intent? = null
    private lateinit var lista: LinkedList
    private val adapter = EmailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_thread)

        registerReceiver(receiver, IntentFilter("com.pedroborba.emailclientapp.ENVIAR_USUARIO"))
        updateUI()
    }

    override fun onStart() {
        super.onStart()
        callService()
    }

    private fun callService(){
        serviceIntent = Intent()

        var bundle = Bundle()
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

    fun linkedToList(lista: LinkedList) : ArrayList<Node> {
        var listaNode : ArrayList<Node> = ArrayList<Node>()

        var node = lista?.head

        while (node != null){
            listaNode.add(node)
            node = node.next
        }

        return listaNode
    }

    fun updateUI(){
        lista = loadList()
        var listaNode : ArrayList<Node> = linkedToList(lista)
        adapter.data(listaNode)

        rvEmail.apply {
            layoutManager = LinearLayoutManager(this@EmailThreadActivity)
            adapter = this@EmailThreadActivity.adapter
        }

        btnClear.setOnClickListener {
            clearDuplicates()
        }
    }

    override fun updateLista(lista: LinkedList) {
        this.lista = lista
    }

    fun clearDuplicates(){
        var listaNode : ArrayList<Node> = linkedToList(this.lista)
        adapter.data(listaNode)
    }
}