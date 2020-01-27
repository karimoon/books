package com.karim.booksapp.di.ui.main

import android.app.Application
import android.content.SharedPreferences
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.repository.BookDbRepository
import com.karim.booksapp.data.repository.BookRepository
import com.karim.booksapp.data.retrofit.BookRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule
{

 @MainScope
 @Provides
 fun provideBookRetrofitService(retrofitBuilder: Retrofit.Builder): BookRetrofitService {
  return retrofitBuilder
   .build()
   .create(BookRetrofitService::class.java)
 }

 @MainScope
 @Provides
 fun provideBookRepository(
  application: Application , bookRetrofitService : BookRetrofitService,
    sharedPreferences: SharedPreferences
 ): BookRepository {
  return BookRepository(
   application,
   bookRetrofitService,sharedPreferences
  )
 }


 }
