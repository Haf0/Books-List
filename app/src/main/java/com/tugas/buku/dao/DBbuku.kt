package com.tugas.buku.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugas.buku.model.Books

@Database(
    entities = [Books::class],
    version = 1
)
abstract class DBbuku : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
    companion object{
        @Volatile
        private var INSTANCE: DBbuku?=null

        fun getDb(context: Context) : DBbuku {
            val temp = INSTANCE

            if(temp != null) {
                return temp
            }

            synchronized(DBbuku::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBbuku::class.java,
                    "room_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}