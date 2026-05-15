package com.example.internship.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internship.R
import com.example.internship.databinding.ItemHomeStayBinding
import com.example.internship.model.HomeStay

class HomeStayAdapter(private var list: List<HomeStay>) : RecyclerView.Adapter<HomeStayAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHomeStayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeStayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.placeName
        holder.binding.tvDistance.text = "${item.distance} km away"
        holder.binding.tvPrice.text = "$${item.price} / night"

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.ivHomestay)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<HomeStay>) {
        list = newList
        notifyDataSetChanged()
    }
}
