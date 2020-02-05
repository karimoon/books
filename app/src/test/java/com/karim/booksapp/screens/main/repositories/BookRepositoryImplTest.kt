package com.karim.booksapp.screens.main.repositories

import com.karim.booksapp.models.Book
import com.karim.booksapp.models.pojos.BooksEntry
import com.karim.booksapp.network.retrofit.BookRetrofitService
import com.karim.booksapp.utils.API_KEY
import com.karim.booksapp.utils.MAX_RESULTS
import com.karim.booksapp.utils.ParserUtil
import com.karim.booksapp.utils.RESTRICT_LANGUAGE
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*


class BookRepositoryImplTest {


    lateinit var BookRepositoryImp : BookRepositoryImpl

    lateinit var query : String

    @Before
    fun setUp() {

        query = "android"
    }

    @Test
    fun configSearchWithDefaultParams(){

        //Arrange
        var data = HashMap<String,String>()


        val expected = "android"

        //Act
        data["q"]=query
        data["maxResults"]= MAX_RESULTS.toString()
        data["key"]= API_KEY
        data["langRestrict"]= RESTRICT_LANGUAGE

        //Assert

        assertEquals(expected , data["q"])
        assertEquals(MAX_RESULTS.toString() , data["maxResults"])

    }

}