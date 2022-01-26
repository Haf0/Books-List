package com.tugas.buku.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tugas.buku.model.Books

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addBuku(data: Books)

    @Delete
    suspend fun deleteBuku(data: Books)

    @Query("Select * From buku")
    fun getFav(): LiveData<List<Books>>
}