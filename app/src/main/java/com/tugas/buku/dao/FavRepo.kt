package com.tugas.buku.dao

import androidx.lifecycle.LiveData
import com.tugas.buku.model.Books

class FavRepo(
    private val favDao:FavoriteDao
) {
    suspend fun addBuku(data: Books)= favDao.addBuku(data)
    suspend fun deleteBuku(data: Books)= favDao.deleteBuku(data)
    fun getfav(): LiveData<List<Books>> = favDao.getFav()
}