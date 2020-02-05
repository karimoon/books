package com.karim.booksapp.screens.main.viewmodels


import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karim.booksapp.utils.LOG_TAG
import com.karim.booksapp.utils.MAX_RESULTS
import com.karim.booksapp.screens.main.helpers.FileHelperWrapper
import com.karim.booksapp.network.helpers.NetworkHelperWrapper
import com.karim.booksapp.models.Book
import com.karim.booksapp.screens.main.repositories.BookDbRepository
import com.karim.booksapp.screens.main.repositories.BookRepository
import com.karim.booksapp.utils.SpUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject
constructor(val networkHelperWrapper : NetworkHelperWrapper, val fileHelperWrapper : FileHelperWrapper,
            val bookRepository: BookRepository, val bookDbRepository: BookDbRepository,
            val sharedPreferences: SharedPreferences) : ViewModel()

{

    val favoriteBooksData = MutableLiveData<List<Book>>()

    val bookData = MutableLiveData<List<Book>>()

    val queryMapSelected = MutableLiveData<HashMap<String,String>>()

    val isNetworkAvailable = MutableLiveData<Boolean>()

    fun networkAvalable(): Boolean {
        return networkHelperWrapper.networkAvalable()
    }


    fun refreshData(mSortBy: String) {

        queryMapSelected.value = queryMapSelected.value?: configSearchWithDefaultParams(SpUtil.getPreferenceString(sharedPreferences, "searchValue")!!)

        queryMapSelected.value?.let {
            it["orderBy"]= mSortBy
            it["maxResults"]= MAX_RESULTS.toString()
            it["startIndex"]= "0"
            searchBooksWithQuery(it)
        }
    }

    fun searchBooksWithQueryWithPagination(queryMap : HashMap<String,String>){
        if(networkAvalable()){
            CoroutineScope(Dispatchers.IO).launch {

                val books = bookRepository.searchBooksFromWebWithPagination(queryMap)

                val actualBooks =(bookData.value?: emptyList())as ArrayList

                actualBooks.addAll(books)

                bookData.postValue(actualBooks)
            }
        }
    }

    fun saveDataToCache(booksData: List<Book>) {

        fileHelperWrapper.saveDataToCache(booksData)
    }


    fun getFavoriteBooks(){
        CoroutineScope(Dispatchers.IO).launch {
            var books = bookDbRepository.getFavoriteBooksFromDatabase()
            books?.let {
                favoriteBooksData.postValue(books)
            }
        }
    }

    fun searchBooksWithQuery(queryMap : HashMap<String,String>){
        if(networkAvalable()){
        CoroutineScope(Dispatchers.IO).launch {
                val books = bookRepository.searchBooksFromWeb(queryMap)
                bookData.postValue(books)
            }
        }
    }

    fun displayBooksFromCache(){
        val data = readDataFromCache()
        bookData.value = data
        Log.i(LOG_TAG, "Using local data")
    }

    private fun readDataFromCache(): List<Book> {

        return fileHelperWrapper.readDataFromCache()
    }

    fun configSearchWithDefaultParams(query : String): HashMap<String, String> {

        return bookRepository.configSearchWithDefaultParams(query)
    }

    fun configSearchWithDefaultParams(title: String, author : String,publisher: String, isbn : String): HashMap<String, String> {

        return bookRepository.configSearchWithDefaultParams(title, author, publisher , isbn)
    }

    fun isNetworkAvailable(): Boolean {
        return networkAvalable()
    }

}
