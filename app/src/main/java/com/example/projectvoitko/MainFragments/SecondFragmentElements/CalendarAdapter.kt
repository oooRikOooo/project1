package com.example.projectvoitko.MainFragments.SecondFragmentElements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectvoitko.R

class CalendarAdapter(var eventsList: ArrayList<CalendarEvents>) : RecyclerView.Adapter<CalendarAdapter.myViewHolder>() {

    class myViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val titleTextView : TextView = itemView.findViewById(R.id.textViewEventTitle)
        val date : TextView = itemView.findViewById(R.id.textViewEventDate)
        val isImportant : CardView = itemView.findViewById(R.id.cardViewImportant)
        val isImportantColor : String = ""


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_list,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentEvent = eventsList[position]

        holder.titleTextView.text = currentEvent.titleEvent
        holder.date.text = currentEvent.date

    }

    override fun getItemCount(): Int {
        return eventsList.size
    }
}