package com.karim.booksapp.network.retrofit

import com.karim.booksapp.models.pojos.BooksEntry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BookRetrofitService {

    @GET("volumes")
    suspend fun getBooksData(@Query("q") query : String , @Query("key") key : String ,
                             @Query("maxResults") maxResults : String,@Query("startIndex") startIndex : String) : Response<BooksEntry>

    @GET("volumes")
    suspend fun getBooksDataParams(@QueryMap options : HashMap<String,String> ) : Response<BooksEntry>


}