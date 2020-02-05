package com.karim.booksapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karim.booksapp.models.Book

@Database(entities = [Book::class] , version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

    companion object {
        @Volatile
        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context: Context) : BookDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "books.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}