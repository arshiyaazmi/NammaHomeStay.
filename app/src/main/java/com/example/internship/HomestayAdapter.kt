package com.example.internship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internship.databinding.DashboardItemBinding
import com.example.internship.model.HomeStay

class HomestayAdapter(private var list: List<HomeStay>) : RecyclerView.Adapter<HomestayAdapter.ViewHolder>() {

    class ViewHolder(val binding: DashboardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DashboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvPlaceName.text = item.placeName
        holder.binding.tvDistanceVal.text = "${item.distance} km"
        holder.binding.tvPriceVal.text = "$${item.price}"

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.ivHotel)
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<HomeStay>) {
        list = newList
        notifyDataSetChanged()
    }
}
