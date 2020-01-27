@file:Suppress("IncorrectScope")

package com.karim.booksapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.karim.booksapp.data.models.Book
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*

class BookDbRepositoryTest {

    @Test
    fun findBookById_return_true(){
        //Arrange
        val book1 = Book()
        book1.id = "1"

        var bookDbRepository : BookDbRepository = mock()

        val list =  arrayListOf(Book("1", "", "",emptyArray<String?>(), "", "","", 0.0, ""))

        whenever(bookDbRepository.getFavoriteBooks())
            .thenReturn(MutableLiveData(list))

        val book = bookDbRepository.getFavoriteBooks()?.value?.get(0)

        assertNotNull(book)
        assertEquals(book1.toString(), book.toString())

    }
}