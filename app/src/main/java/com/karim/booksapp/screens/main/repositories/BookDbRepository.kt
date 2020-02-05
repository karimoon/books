package com.karim.booksapp.screens.main.repositories

import com.karim.booksapp.models.Book

interface BookDbRepository {
    suspend fun getFavoriteBooksFromDatabase(): List<Book>

    suspend fun insertEntry(book: Book)

    suspend fun deleteEntry(book: Book)

    suspend fun findBookById(id : String): Book
}