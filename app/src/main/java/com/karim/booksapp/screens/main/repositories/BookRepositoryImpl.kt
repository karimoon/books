package com.karim.booksapp.screens.main.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.karim.booksapp.models.Book
import com.karim.booksapp.network.retrofit.BookRetrofitService
import com.karim.booksapp.models.pojos.BooksEntry
import com.karim.booksapp.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BookRepositoryImpl
@Inject
constructor(val bookRetrofitService: BookRetrofitService) :
    BookRepository
{
    //done passer une interface PermissionRepository dans le constructeur

    //done retirer application faire un Wrapper pour FileHelper (utilise une interface pour definir le Wrapper)
    // done pas de liveData dans tes repository


    override suspend fun searchBooksFromWeb(query: HashMap<String,String>): List<Book> {
            val serviceData = bookRetrofitService.getBooksDataParams(query).body()?: BooksEntry()

            return ParserUtil.getBooksFromBooksEntry(serviceData)

    }

    override suspend fun searchBooksFromWebWithPagination(query: HashMap<String,String>): List<Book> {

        Log.i(LOG_TAG, "Calling web service")
        val serviceData = bookRetrofitService.getBooksDataParams(query).body()?: BooksEntry()
        return ParserUtil.getBooksFromBooksEntry(serviceData)

    }

    override fun configSearchWithDefaultParams(query : String): HashMap<String, String> {
        var data = HashMap<String,String>()

        data["q"]=query
        data["maxResults"]= MAX_RESULTS.toString()
        data["key"]= API_KEY
        data["langRestrict"]= RESTRICT_LANGUAGE

        return data
    }

    override fun configSearchWithDefaultParams(title: String, author : String,publisher: String, isbn : String): HashMap<String, String> {
        val sb = StringBuilder()

        if (!title.isEmpty()) sb.append("intitle:" + title + "+")
        if (!author.isEmpty()) sb.append("inauthor:" + author + "+")
        if (!publisher.isEmpty()) sb.append("inpublisher:" + publisher + "+")
        if (!isbn.isEmpty()) sb.append("isbn:" + isbn + "+")
        //removes the last character
        sb.setLength(sb.length - 1)
        val query = sb.toString()

        var data = HashMap<String,String>()

        data["q"]=query
        data["maxResults"]= MAX_RESULTS.toString()
        data["key"]= API_KEY
        data["langRestrict"]= RESTRICT_LANGUAGE

        return data
    }
}