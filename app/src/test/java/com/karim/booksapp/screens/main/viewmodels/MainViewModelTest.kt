package com.karim.booksapp.screens.main.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.karim.booksapp.models.Book
import com.karim.booksapp.network.helpers.NetworkHelperWrapper
import com.karim.booksapp.network.retrofit.BookRetrofitService
import com.karim.booksapp.screens.main.helpers.FileHelperWrapper
import com.karim.booksapp.screens.main.repositories.BookDbRepository
import com.karim.booksapp.screens.main.repositories.BookRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var mainViewModel : MainViewModel

    @Mock
    lateinit var networkHelperWrapper : NetworkHelperWrapper

    @Mock
    lateinit var fileHelperWrapper : FileHelperWrapper

    @Mock
    lateinit var bookRepository: BookRepository

    @Mock
    lateinit var bookDbRepository: BookDbRepository

    @Mock
    lateinit var sharedPreferences: SharedPreferences


    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        //networkHelperWrapper  = mock()
        fileHelperWrapper  = mock()
        bookRepository  = mock()
        bookDbRepository  = mock()
        sharedPreferences = mock()

        mainViewModel = MainViewModel(networkHelperWrapper,fileHelperWrapper,bookRepository
        ,bookDbRepository,sharedPreferences)

    }

    @Test
    fun searchBooksWithQuery_return_false(){
        //Arrange

        val query = HashMap<String,String>()

        val book1 = Book()
        val book2 = Book()
        val book3 = Book()

        book1.id = "1"
        book2.id = "2"
        book3.id = "3"

        val list : List<Book> = listOf(book1,book2,book3)

        var result = emptyList<Book>()

        //Act

        `when`(networkHelperWrapper.networkAvalable())
            .thenReturn(false)

        /*whenever(networkHelperWrapper.networkAvalable())
            .thenReturn(true)*/

        runBlocking {

            `when`(bookRepository.searchBooksFromWeb(query))
                .thenReturn(list)

            if(mainViewModel.isNetworkAvailable()){
                result = bookRepository.searchBooksFromWeb(query)
            }
        }

        //Assert

        assertEquals(0, result.size)
    }

    @Test
    fun searchBooksWithQuery_return_true() {

        //Arrange

        val query = HashMap<String,String>()

        val book1 = Book()
        val book2 = Book()
        val book3 = Book()

        book1.id = "1"
        book2.id = "2"
        book3.id = "3"

        val list : List<Book> = listOf(book1,book2,book3)

        var result = emptyList<Book>()

        //Act

        `when`(networkHelperWrapper.networkAvalable())
            .thenReturn(true)

        /*whenever(networkHelperWrapper.networkAvalable())
            .thenReturn(true)*/

        runBlocking {

            `when`(bookRepository.searchBooksFromWeb(query))
                .thenReturn(list)

            if(mainViewModel.isNetworkAvailable()){
                result = bookRepository.searchBooksFromWeb(query)
            }
        }

        //Assert
        assertNotNull(result)

        assertEquals(list, result)
    }

    @Test
    fun getFavoriteBooks_return_true() {

        //Arrange

        val book1 = Book()
        val book2 = Book()
        val book3 = Book()

        book1.id = "1"
        book2.id = "2"
        book3.id = "3"

        val list : List<Book> = listOf(book1,book2,book3)

        var result = emptyList<Book>()

        //Act

        runBlocking {

            `when`(bookDbRepository.getFavoriteBooksFromDatabase())
                .thenReturn(list)

                result = bookDbRepository.getFavoriteBooksFromDatabase()

        }

        //Assert
        assertNotNull(result)

        assertEquals(list, result)
    }
}