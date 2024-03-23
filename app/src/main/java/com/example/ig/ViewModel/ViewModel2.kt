package com.example.ig.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ig.Repository.FavRepository
import com.example.ig.db.Fav

class ViewModel2(application: Application) :
    ViewModel() {
    private val mFavRepository: FavRepository = FavRepository(application)

    fun getAllFav(): LiveData<List<Fav>> = mFavRepository.getAllFav()
    fun getFav(username: String): LiveData<List<Fav>> = mFavRepository.getFavoriteUserByUsername(username)
}
