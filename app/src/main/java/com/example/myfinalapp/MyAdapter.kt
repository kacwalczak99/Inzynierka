package com.example.myfinalapp

import android.app.Activity
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context : Activity, private val arrayList : ArrayList<Person>) : ArrayAdapter<Person>(context,
    R.layout.list_item, arrayList)      {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val activities : TextView = view.findViewById(R.id.activity_name)
        val person_name : TextView = view.findViewById(R.id.name_list)
        val activity_time : TextView = view.findViewById(R.id.time)
        val checkbox : ImageView = view.findViewById(R.id.checkbox)
        val person_icon : ImageView = view.findViewById(R.id.person_icon)

        imageView.setImageResource(arrayList[position].imageID)
        activities.text = arrayList[position].activities
        person_name.text = arrayList[position].name
        activity_time.text = arrayList[position].activityTime
        checkbox.setImageResource(arrayList[position].checkbox)
        person_icon.setImageResource(arrayList[position].person_icon)



        return view
    }
}