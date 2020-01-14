package com.karim.booksapp

import androidx.room.Room
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.database.BookDatabase
import org.junit.After
import org.junit.Before

import androidx.test.core.app.ApplicationProvider

open class BooksDatabaseTest {

    private var booksDatabase: BookDatabase? = null


    fun getBookDao(): BookDao {
        return booksDatabase!!.bookDao()
    }

    @Before
    fun init() {
        booksDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java!!
        ).build()
    }

    @After
    fun finish() {
        booksDatabase!!.close()
    }
}