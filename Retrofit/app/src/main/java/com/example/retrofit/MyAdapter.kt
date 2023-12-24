package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemMydataBinding

class MyAdapter(var itemList : List<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemMydataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyData) {
            binding.itemTitle.text = item.title
            binding.itemContent.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMydataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setData(items: List<MyData>) {
        itemList = items
        notifyDataSetChanged()
    }
}