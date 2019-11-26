package com.jshealth.demoapp.contact_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.jshealth.demoapp.ContactListActivity
import com.jshealth.demoapp.MainActivity
import com.jshealth.demoapp.R


class ContactAdaptor(var mContext : ContactListActivity, var contact_list: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdaptor.ViewHolder>() {


    //this method is returning the view for each item in the dialyList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdaptor.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_contact_item, parent, false)
        return ViewHolder(v)
    }
   fun update(contact_list: ArrayList<Contact>){
       this.contact_list=contact_list
       notifyDataSetChanged()
   }
    override fun onBindViewHolder(holder: ContactAdaptor.ViewHolder, position: Int) {
        holder.tvName.text = contact_list.get(position).name
        holder.tvPhone.text = contact_list.get(position).numbers[0].number!!
       // holder.tvEmail.text = contact_list.get(position).emails[0].address!!
    }

    //this method is giving the size of the dialyList
    override fun getItemCount(): Int {
        return contact_list.size

    }

    //the class is hodling the dialyList view
     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val tvName  = itemView.findViewById(R.id.tvName) as TextView
            val tvPhone  = itemView.findViewById(R.id.tvPhone) as TextView
            val tvEmail  = itemView.findViewById(R.id.tvEmail) as TextView

    }


}