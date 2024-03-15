package com.example.ig.db

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.Query

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: ContactsContract.CommonDataKinds.Note)

    @Update
    fun update(note: ContactsContract.CommonDataKinds.Note)

    @Delete
    fun delete(note: ContactsContract.CommonDataKinds.Note)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllFav(): LiveData<List<ContactsContract.CommonDataKinds.Note>>
}