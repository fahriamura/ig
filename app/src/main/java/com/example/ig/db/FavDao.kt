package com.example.ig.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fav: Fav)

    @Update
    fun update(fav: Fav)

    @Delete
    fun delete(fav: Fav)

    @Query("SELECT * from FavData")
    fun getAllFav(): LiveData<List<Fav>>
    @Query("SELECT * FROM FavData WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<List<Fav>>

}
