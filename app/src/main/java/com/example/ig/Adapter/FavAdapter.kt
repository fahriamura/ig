package com.example.ig.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ig.databinding.DicogramCardBinding
import com.example.ig.db.Fav

@Suppress("DEPRECATION")
class FavAdapter : ListAdapter<Fav, FavAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
    private var onItemClickCallback: OnItemClickCallback? = null

    inner class MyViewHolder(private val binding: DicogramCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Fav) { // Change parameter type to Fav
            binding.tvListName.text = item.username // Assuming username and avatarUrl are properties of Fav class
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .into(binding.imgListPhoto)
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item, adapterPosition)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Fav>() {
            override fun areItemsTheSame(oldItem: Fav, newItem: Fav): Boolean {
                return oldItem.username == newItem.username // Adjust to your identifier property
            }

            override fun areContentsTheSame(oldItem: Fav, newItem: Fav): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Fav, position: Int)
    }
    fun setOnItemCLickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DicogramCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}
