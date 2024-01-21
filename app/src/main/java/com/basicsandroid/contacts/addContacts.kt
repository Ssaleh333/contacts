package com.basicsandroid.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import com.basicsandroid.contacts.databinding.ActivityAddContantBinding


class addContacts : AppCompatActivity() {
   lateinit var binding :ActivityAddContantBinding
   lateinit var name:String
   lateinit var phone:String
   lateinit var description:String
   lateinit var adapter: ContactAdapter
    var contactList= mutableListOf<Contacts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddContantBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startRecyclerView()
        onSavedButton()


    }

    private fun startRecyclerView() {
        adapter=ContactAdapter(contactList)
        binding.recV.adapter=adapter
        adapter.Listner=object :ContactAdapter.ContantClickListner{
            override fun onClick(contact: Contacts) {

            }

        }
    }

    private fun onSavedButton() {
        binding.but.setOnClickListener {
            if (!validateTextField()) {
                return@setOnClickListener
            }
            name = binding.nameEdt.text?.trim().toString()
            phone = binding.phoneEdt.text?.trim().toString()
            description = binding.descEdt.text?.trim().toString()
            val contact = Contacts(name, phone, description)
            contactList.add(contact)
            adapter.notifyItemInserted(contactList.size-1)
        }
    }

    fun validateTextField():Boolean{
        name=binding.nameEdt.text?.trim().toString()
        phone=binding.phoneEdt.text?.trim().toString()
        binding.nameTil.error=validateName(name)
        binding.phoneTil.error=validateName(phone)

        return validateName(name)==null && validatePhone(phone)==null
    }


    fun validateName(name:String):String?{
        if (name.isEmpty())
            return "Required"
        if (name.trim().length<3)
            return "Name can't be less than 3 chara"
        return null
    }

    fun validatePhone(name:String):String?{
        if (name.isEmpty())
            return "Required"
        if (name.trim().length<11)
            return "Phone can't be less than 11 digits"
        return null
    }
}