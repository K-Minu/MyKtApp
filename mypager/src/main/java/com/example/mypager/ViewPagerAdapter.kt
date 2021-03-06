package com.example.mypager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(var list: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)){

        val item = itemView.findViewById<ImageView>(R.id.imageView_item)

        fun onBind(res: Int){
            item.setImageResource(res)
        }
    }
}