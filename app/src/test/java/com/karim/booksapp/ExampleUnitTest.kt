@file:Suppress("IncorrectScope")

package com.karim.booksapp

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.models.Book
import com.karim.booksapp.data.repository.BookDbRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Matchers
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
}
