package com.karim.booksapp.screens.main.repositories

import com.karim.booksapp.room.BookDao
import com.karim.booksapp.models.Book
import javax.inject.Inject

class BookDbRepositoryImpl
@Inject
constructor(val bookDao : BookDao) : BookDbRepository {

    override suspend fun getFavoriteBooksFromDatabase(): List<Book> {
        return bookDao.getAll()
    }

    override suspend fun insertEntry(book: Book){
        return bookDao.insertBook(book)
    }

    override suspend fun deleteEntry(book: Book){
        return bookDao.delete(book)
    }

    override suspend fun findBookById(id : String): Book {
        return bookDao.findBookById(id)
    }


}