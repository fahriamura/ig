package com.example.ig.Helper

import androidx.recyclerview.widget.DiffUtil
import com.example.ig.db.Fav


class FavDiffCallback(private val oldFavList: List<Fav>, private val newFavList: List<Fav>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavList.size
    override fun getNewListSize(): Int = newFavList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavList[oldItemPosition].id == newFavList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFav = oldFavList[oldItemPosition]
        val newFav = newFavList[newItemPosition]
        return oldFav.username == newFav.username && oldFav.avatarUrl == newFav.avatarUrl
    }

}