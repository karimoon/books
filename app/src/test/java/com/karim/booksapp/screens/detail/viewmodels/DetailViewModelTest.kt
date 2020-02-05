package com.karim.booksapp.screens.detail.viewmodels

import com.karim.booksapp.models.Book
import com.karim.booksapp.screens.main.repositories.BookDbRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify


class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @Spy
    lateinit var bookDbRepository: BookDbRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        detailViewModel = DetailViewModel(bookDbRepository)
    }

    @Test
    fun removeFromFavorites() {

        //Arrange

        val book = Book()
        val bookFound = Book()
        book.id ="id_of_favorite_book"

        //Act

        runBlocking {
            doReturn(bookFound).`when`(bookDbRepository).findBookById("id_of_favorite_book")

            doReturn(kotlin.Unit).`when`(bookDbRepository).deleteEntry(book)

            detailViewModel.removeFromFavorites(book)

            //Assert

            verify(bookDbRepository).deleteEntry(book)
        }
    }

    @Test
    fun markAsFavorite() {

        //Arrange

        val book = Book()
        book.id ="not_id_of_favorite_book"
        val bookFound = null

        //Act

        runBlocking {
            doReturn(bookFound).`when`(bookDbRepository).findBookById("not_id_of_favorite_book")

            doReturn(kotlin.Unit).`when`(bookDbRepository).insertEntry(book)

            detailViewModel.markAsFavorite(book)

            //Assert

            verify(bookDbRepository).insertEntry(book)

        }

    }

    @Test
    fun isFavorite_return_true() {

        //Arrange

        val book = Book()
        val bookFound = Book()
        book.id ="id_of_favorite_book"

        //Act

        runBlocking {

            doReturn(bookFound).`when`(bookDbRepository).findBookById("id_of_favorite_book")
            val result = detailViewModel.isFavorite(book)

            verify(bookDbRepository).findBookById("id_of_favorite_book")

            //Assert

            Assert.assertEquals(true, result)
        }
    }

    @Test
    fun isFavorite_return_false() {

        //Arrange

        val book = Book()
        book.id ="not_id_of_favorite_book"
        val bookFound = null

        //Act

        runBlocking {
            doReturn(bookFound).`when`(bookDbRepository).findBookById("not_id_of_favorite_book")
            val result = detailViewModel.isFavorite(book)

            //Assert

            Assert.assertEquals(false, result)

        }
    }
}