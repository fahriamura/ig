package com.example.ig.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ig.db.Fav
import com.example.ig.db.FavDao
import com.example.ig.db.FavRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository(application: Application) {
    private val mFavDao: FavDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavRoomDatabase.getDatabase(application)
        mFavDao = db.favDao()
    }

    fun getAllFav(): LiveData<List<Fav>> = mFavDao.getAllFav()

    fun insert(fav: Fav) {
        executorService.execute { mFavDao.insert(fav) }
    }

    fun delete(fav: Fav) {
        executorService.execute { mFavDao.delete(fav) }
    }
    fun getFavoriteUserByUsername(username: String): LiveData<List<Fav>> = mFavDao.getFavoriteUserByUsername(username)
}
