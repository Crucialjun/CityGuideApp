package com.example.cityguideapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Featured_Adapter(val featuredLocations: ArrayList<FeaturedHelper>) :
    RecyclerView.Adapter<Featured_Adapter.FeaturedAdapaterViewHolder>() {


    inner class FeaturedAdapaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.featured_image)
        val title = itemView.findViewById<TextView>(R.id.featured_title)
        val description = itemView.findViewById<TextView>(R.id.featured_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedAdapaterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.featured_design_layout, parent, false)

        return FeaturedAdapaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedAdapaterViewHolder, position: Int) {

        val featuredHelper = featuredLocations[position]

        holder.image.setImageResource(featuredHelper.image)
        holder.title.text = featuredHelper.title
        holder.description.text = featuredHelper.description
    }

    override fun getItemCount(): Int {
        return featuredLocations.size
    }
}