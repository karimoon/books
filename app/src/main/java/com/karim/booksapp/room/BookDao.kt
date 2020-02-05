package com.karim.booksapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.karim.booksapp.models.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun findBookById(id: String): Book

    @Insert
    suspend fun insertBook(book: Book)

    @Insert
    suspend fun insertBooks(book: List<Book>)

    @Query("DELETE FROM books")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(book: Book)
}