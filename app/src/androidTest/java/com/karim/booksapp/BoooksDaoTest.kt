package com.karim.booksapp

import android.database.sqlite.SQLiteConstraintException
import com.karim.booksapp.data.models.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class BoooksDaoTest : BooksDatabaseTest() {



    @Test
    fun insertBooksWithDifferentIds() = runBlocking {
        insertBooksWithDifferentIds_return_true_scope()
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertBooksWithSameIds() = runBlocking {
        insertBooksWithSameIds_throwSQLiteConstraintException_scope()

    }


    fun CoroutineScope.insertBooksWithDifferentIds_return_true_scope() {
        // This coroutines `Job` is not shared with the test code
        launch {
            insertBooksWithDifferentIds_return_true()      // executes eagerly when foo() is called due to runBlockingTest
            println("karim instrumental test") // executes eagerly when foo() is called due to runBlockingTest
        }
    }


    fun CoroutineScope.insertBooksWithSameIds_throwSQLiteConstraintException_scope() {
        // This coroutines `Job` is not shared with the test code
        launch {
            insertBooksWithSameIds_throwSQLiteConstraintException()      // executes eagerly when foo() is called due to runBlockingTest
        }
    }

    suspend fun bar() {}


    @Throws(Exception::class)
    suspend fun insertBooksWithDifferentIds_return_true() {

        //Arrange
        val book1 = Book()
        val book2 = Book()
        val book3 = Book()

        book1.id = "1"
        book2.id = "2"
        book3.id = "3"


        //Act
        getBookDao().insertBook(book1)
        getBookDao().insertBook(book2)
        getBookDao().insertBook(book3)
        val books = getBookDao().getAll()
        println(books.toString())

        //Assert
        assertEquals(books.size, 3)
    }


    @Throws(Exception::class)
    suspend fun insertBooksWithSameIds_throwSQLiteConstraintException(){

        //Arrange
        val book1 = Book()
        val book2 = Book()

        book1.id = "1"
        book2.id = "1"

        //Act
        getBookDao().insertBook(book1)
        getBookDao().insertBook(book2)

    }

}