package com.example.ig.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.ig.Repository.FavRepository
import com.example.ig.db.Fav


class FavAddUpdateViewModel(application: Application) : ViewModel() {

    private val mFavRepository: FavRepository = FavRepository(application)

    fun insert(note: Fav) {
        mFavRepository.insert(note)
    }

    fun delete(note: Fav) {
        mFavRepository.delete(note)
    }




}