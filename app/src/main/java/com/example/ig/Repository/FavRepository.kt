package com.example.ig.Repository

import android.app.Application
import android.provider.ContactsContract
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

    fun getAllFav(): LiveData<List<ContactsContract.CommonDataKinds.Note>> = mFavDao.getAllFav()

    fun insert(note: ContactsContract.CommonDataKinds.Note) {
        executorService.execute { mFavDao.insert(note) }
    }

    fun delete(note: ContactsContract.CommonDataKinds.Note) {
        executorService.execute { mFavDao.delete(note) }
    }

    fun update(note: ContactsContract.CommonDataKinds.Note) {
        executorService.execute { mFavDao.update(note) }
    }
}