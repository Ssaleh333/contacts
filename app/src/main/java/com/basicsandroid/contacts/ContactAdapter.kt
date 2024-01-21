package com.basicsandroid.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val contactList: List<Contacts>) :RecyclerView.Adapter<ContactAdapter.myViewHolder>(){
    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameItem:TextView=itemView.findViewById(R.id.name_item)
        val phoneItem:TextView=itemView.findViewById(R.id.phone_item)
        fun bind(contact: Contacts){
            nameItem.text=contact.name
            phoneItem.text=contact.phone

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int =contactList.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val contact=contactList.get(position)
        holder.bind(contact)
        holder.itemView.setOnClickListener{
            Listner?.onClick(contact)
        }
    }
    interface ContantClickListner{
        fun onClick(contact: Contacts)
    }
    var Listner:ContantClickListner?=null
}