package com.karim.booksapp.utils

import com.karim.booksapp.models.Book
import com.karim.booksapp.models.pojos.BooksEntry
import com.karim.booksapp.models.pojos.ImageLinks
import org.json.JSONException
import org.json.JSONObject

class ParserUtil{

    companion object{
        fun getBooksFromJson( json : String) : List<Book> {

            val ID = "id"
            val TITLE = "title"
            val SUBTITLE = "subtitle"
            val AUTHORS = "authors"
            val PUBLISHER = "publisher"
            val PUBLISHED_DATE = "publishedDate"
            val ITEMS = "items"
            val VOLUMEINFO = "volumeInfo"
            val DESCRIPTION = "description"
            val RATING = "averageRating"
            val IMAGEINFO = "imageLinks"
            val THUMBNAIL = "thumbnail"

            val books = ArrayList<Book>()

            try {
                val jsonBooks = JSONObject(json)
                val arrayBooks = jsonBooks.getJSONArray(ITEMS)
                val numberOfBooks = arrayBooks.length()
                for (i in 0 until numberOfBooks) {
                    val bookJSON = arrayBooks.getJSONObject(i)
                    val volumeInfoJSON = bookJSON.getJSONObject(VOLUMEINFO)
                    var imageLinksJSON: JSONObject? = null
                    if (volumeInfoJSON.has(IMAGEINFO)) {
                        imageLinksJSON = volumeInfoJSON.getJSONObject(IMAGEINFO)
                    }
                    var authorNum: Int
                    try {
                        authorNum = volumeInfoJSON.getJSONArray(AUTHORS).length()
                    } catch (e: Exception) {
                        authorNum = 0
                    }

                    val authors = arrayOfNulls<String>(authorNum)
                    for (j in 0 until authorNum) {
                        authors[j] = volumeInfoJSON.getJSONArray(AUTHORS).get(j).toString()
                    }
                    val book = Book(
                        bookJSON.getString(ID),
                        volumeInfoJSON.getString(TITLE),
                        if (volumeInfoJSON.isNull(SUBTITLE)) "" else volumeInfoJSON.getString(
                            SUBTITLE
                        ),
                        authors,
                        if (volumeInfoJSON.isNull(PUBLISHER)) "" else volumeInfoJSON.getString(
                            PUBLISHER
                        ),
                        if (volumeInfoJSON.isNull(PUBLISHED_DATE)) "" else volumeInfoJSON.getString(
                            PUBLISHED_DATE
                        ),
                        if (volumeInfoJSON.isNull(DESCRIPTION)) "" else volumeInfoJSON.getString(
                            DESCRIPTION
                        ),
                        if (volumeInfoJSON.isNull(RATING)) 0.0 else volumeInfoJSON.getDouble(
                            RATING
                        ),
                        if (imageLinksJSON == null) "" else imageLinksJSON.getString(THUMBNAIL)
                    )
                    books.add(book)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return books
        }


        fun getBooksFromBooksEntry( booksEntry: BooksEntry) : List<Book> {


            val books = ArrayList<Book>()

            try {
                //val jsonBooks = JSONObject(json)
                val arrayBooks = booksEntry.items
                val numberOfBooks = arrayBooks?.size

                numberOfBooks?.let {

                    for (i in 0 until it) {
                        val bookJSON = arrayBooks.get(i)
                        val volumeInfoJSON = bookJSON.volumeInfo
                        var imageLinksJSON: ImageLinks =
                            ImageLinks()
                        if (volumeInfoJSON!!.imageLinks != null) {
                            imageLinksJSON = volumeInfoJSON!!.imageLinks!!
                        }
                        var authorNum: Int
                        try {
                            authorNum = volumeInfoJSON.authors!!.size
                        } catch (e: Exception) {
                            authorNum = 0
                        }

                        val authors = arrayOfNulls<String>(authorNum)
                        for (j in 0 until authorNum) {
                            authors[j] = volumeInfoJSON.authors!!.get(j).toString()
                        }
                        val book = Book(
                            bookJSON.id!!,
                            volumeInfoJSON.title!!,
                            volumeInfoJSON.subtitle ?: ""
                            ,
                            authors,
                            volumeInfoJSON.publisher ?: ""
                            ,
                            volumeInfoJSON.publishedDate ?: ""
                            ,
                            volumeInfoJSON.description ?: "",
                            volumeInfoJSON.averageRating ?: 0.0,
                            imageLinksJSON.thumbnail ?: ""

                        )
                        books.add(book)
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return books
        }
    }
}