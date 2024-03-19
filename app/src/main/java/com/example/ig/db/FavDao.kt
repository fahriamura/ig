package com.example.ig.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: Fav)

    @Update
    fun update(fav: Fav)

    @Delete
    fun delete(fav: Fav)

    @Query("SELECT * from FavData ORDER BY id ASC")
    fun getAllFav(): LiveData<List<Fav>>

    @Query("SELECT * FROM FavData WHERE username = :username LIMIT 1")
    fun getFavByUsername(username: String): Fav?
}
