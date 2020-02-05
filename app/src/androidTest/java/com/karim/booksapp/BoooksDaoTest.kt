package com.karim.booksapp

import android.database.sqlite.SQLiteConstraintException
import com.karim.booksapp.models.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class BoooksDaoTest : BooksDatabaseTest() {


    @Test
    @Throws(Exception::class)
    fun insertBooksWithDifferentIds_return_true() {

        //Arrange
        val book1 = Book()
        val book2 = Book()
        val book3 = Book()

        book1.id = "1"
        book2.id = "2"
        book3.id = "3"

        runBlocking {

            //Act
            getBookDao().insertBook(book1)
            getBookDao().insertBook(book2)
            getBookDao().insertBook(book3)
            val books = getBookDao().getAll()
            println(books.toString())

            //Assert
            assertEquals(books.size, 3)
        }

    }


    @Test(expected = SQLiteConstraintException::class)
    @Throws(Exception::class)
    fun insertBooksWithSameIds_throwSQLiteConstraintException(){

        //Arrange
        val book1 = Book()
        val book2 = Book()

        book1.id = "1"
        book2.id = "1"

        //Act

        runBlocking {

            getBookDao().insertBook(book1)
            getBookDao().insertBook(book2)
        }


    }

}