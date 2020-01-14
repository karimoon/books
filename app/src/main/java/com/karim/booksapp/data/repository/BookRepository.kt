package com.karim.booksapp.data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.karim.booksapp.*
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.models.Book
import com.karim.booksapp.data.database.BookDatabase
import com.karim.booksapp.data.retrofit.BookRetrofitService
import com.karim.booksapp.data.models.pojos.BooksEntry
import com.karim.booksapp.data.utils.ParserUtil
import com.karim.booksapp.utilities.FileHelper
import com.karim.booksapp.utilities.SpUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class BookRepository
@Inject
constructor(val app :Application , val bookRetrofitService: BookRetrofitService,
            val bookDao : BookDao
) {

    val isNetworkAvailable = MutableLiveData<Boolean>()
    val bookData = MutableLiveData<List<Book>>()
    val favoriteBooksData = MutableLiveData<List<Book>>()

    val queryMapSelected = MutableLiveData<HashMap<String,String>>()


    fun getBooksFromJsonFile() {

        val json = FileHelper.getTextFromResources(app, R.raw.books_mock_data)

        val books = ParserUtil.getBooksFromJson(json)

        bookData.value= books
    }

    @WorkerThread
    suspend fun searchBooksFromWeb(query: HashMap<String,String>){

        if(networkAvalable()){

            val serviceData = bookRetrofitService.getBooksDataParams(query).body()?: BooksEntry()


            val books = ParserUtil.getBooksFromBooksEntry(serviceData)

            bookData.postValue(books)
            saveDataToCache(books)
        }

    }

    @WorkerThread
    suspend fun searchBooksFromWebWithPagination(query: HashMap<String,String>){

        if(networkAvalable()){
            Log.i(LOG_TAG, "Calling web service")

            val serviceData = bookRetrofitService.getBooksDataParams(query).body()?: BooksEntry()
            val books = ParserUtil.getBooksFromBooksEntry(serviceData)

            val actualBooks =(bookData.value?: emptyList())as ArrayList

            actualBooks.addAll(books)

            bookData.postValue(actualBooks)
            try {
                saveDataToCache(actualBooks)
            } catch (e: ConcurrentModificationException) {
                e.printStackTrace()

            }
        }
    }

    @WorkerThread
    suspend fun getFavoriteBooksFromDatabase(){
        var books = bookDao.getAll()

        books?.let {
            favoriteBooksData.postValue(books)
        }
    }

    fun refreshDataFromWeb(mSortBy: String) {

        queryMapSelected.value = queryMapSelected.value?: configSearchWithDefaultParams(SpUtil.getPreferenceString(app, "searchValue")!!)

        queryMapSelected.value?.let {
            it["orderBy"]= mSortBy
            it["maxResults"]= MAX_RESULTS.toString()
            it["startIndex"]= "0"
            searchBooksWithQueryFromWeb(it)
        }

    }

    fun configSearchWithDefaultParams(query : String): HashMap<String, String> {
        var data = HashMap<String,String>()

        data["q"]=query
        data["maxResults"]= MAX_RESULTS.toString()
        data["key"]= API_KEY
        data["langRestrict"]= RESTRICT_LANGUAGE

        return data
    }

    @Suppress("DEPRECATION")
    fun networkAvalable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

     private fun saveDataToCache(monsterData: List<Book>) {
        if (ContextCompat.checkSelfPermission(
                app,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            val moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, Book::class.java)
            val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
            val json = adapter.toJson(monsterData)
            FileHelper.saveTextToFile(app, json)
        }
    }

    private fun readDataFromCache(): List<Book> {
        val json = FileHelper.readTextFile(app)
        if (json == null) {
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Book::class.java)
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

    fun getFavoriteBooks() {

        CoroutineScope(Dispatchers.IO).launch {
            getFavoriteBooksFromDatabase()
        }
    }


    fun searchBooksWithQueryFromWebWithPagination(query : HashMap<String,String>) {

        CoroutineScope(Dispatchers.IO).launch {
            searchBooksFromWebWithPagination(query)
        }
    }

    fun searchBooksWithQueryFromWeb(query : HashMap<String,String>) {

        CoroutineScope(Dispatchers.IO).launch {
            searchBooksFromWeb(query)
        }
    }

    fun displayBooksFromCache() {
        val data = readDataFromCache()
        bookData.value = data
        Log.i(LOG_TAG, "Using local data")    }

}