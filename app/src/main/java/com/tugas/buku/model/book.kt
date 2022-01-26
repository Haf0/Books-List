package com.tugas.buku.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Book(
    val result : List<Books>
)

@Entity(tableName = "buku")
data class Books(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id_")
    val id: Int,

    @ColumnInfo(name = "judul")
    val judul: String,

    @ColumnInfo(name = "penulis")
    val penulis: String,

    @ColumnInfo(name = "tahun_terbit")
    val tahun_terbit: String,

    @ColumnInfo(name = "kategori")
    val kategori: String,

    @ColumnInfo(name = "penerbit")
    val penerbit: String,

    @ColumnInfo(name = "sinopsis")
    val sinopsis: String,

    @ColumnInfo(name = "gambar")
    val gambar: String,

    @ColumnInfo(name = "harga")
    val harga: Int
)
