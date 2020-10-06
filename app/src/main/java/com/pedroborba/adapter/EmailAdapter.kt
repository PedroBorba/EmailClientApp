package com.pedroborba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedroborba.emailclientapp.R
import com.pedroborba.model.LinkedList
import kotlinx.android.synthetic.main.row_email_thread.view.*

class EmailAdapter : RecyclerView.Adapter<EmailAdapter.ViewHolder>(){

    private var lista : ArrayList<LinkedList.Node>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_email_thread, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lista?.let {
            holder.bind(it[position])
        }
    }

    fun data(lista: ArrayList<LinkedList.Node>){
        this.lista = lista
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(node: LinkedList.Node) {
            itemView.txtTitle.text = node.data.toString() + " - " + node.title
            itemView.txtMessage.text = node.message
        }

    }

}