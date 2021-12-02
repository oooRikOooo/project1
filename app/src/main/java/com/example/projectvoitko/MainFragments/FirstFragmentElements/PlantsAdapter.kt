package com.example.projectvoitko.MainFragments.FirstFragmentElements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectvoitko.R
import com.google.android.material.imageview.ShapeableImageView

class PlantsAdapter(private var plantsList: ArrayList<Plants>) : RecyclerView.Adapter<PlantsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val titleImage : ImageView = itemView.findViewById(R.id.imageViewRecyclerView)
        val tvTitle : TextView = itemView.findViewById(R.id.textViewTitle)
        val tvDescription : TextView = itemView.findViewById(R.id.textViewDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_recycler_view,parent,false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = plantsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvTitle.text = currentItem.title
        holder.tvDescription.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return plantsList.size
    }

    public fun filterList(filteredList : ArrayList<Plants>){
        plantsList = filteredList
        notifyDataSetChanged()
    }
}