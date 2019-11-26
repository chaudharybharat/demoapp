package com.jshealth.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshealth.demoapp.contact_model.Contact
import com.jshealth.demoapp.contact_model.ContactAdaptor
import com.jshealth.demoapp.contact_model.ContactFetcher

import kotlinx.android.synthetic.main.activity_contact_list.*
import java.nio.file.Files.size
import androidx.core.app.ComponentActivity.ExtraData
import androidx.arch.core.util.Cancellable
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class ContactListActivity : AppCompatActivity() {
    var listContacts: ArrayList<Contact> = ArrayList()
    lateinit var adapterContacts : ContactAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        listContacts = ContactFetcher(this).fetchAll()
        adapterContacts = ContactAdaptor(this, listContacts!!)
        rv_contact.layoutManager=LinearLayoutManager(this)
        rv_contact.adapter=adapterContacts

        edt_customer.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(str: Editable?) {

            }

            override fun beforeTextChanged(str: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(search: CharSequence?, start: Int, before: Int, count: Int) {
                var search_list: ArrayList<Contact> = ArrayList()
                if(!search.toString().equals("")){
                    for (i in 0 until listContacts!!.size) {
                        if (listContacts.get(i).name.contains(search.toString(),ignoreCase = true)) {
                            search_list.add(listContacts.get(i))
                        }
                    }
                }else{
                    search_list.addAll(listContacts)
                }

                adapterContacts.update(search_list)
            }

        })
    }
}
