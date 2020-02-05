package com.karim.booksapp.di.screens.main

import android.app.Application
import com.karim.booksapp.screens.main.helpers.FileHelperWrapper
import com.karim.booksapp.screens.main.helpers.FileHelperWrapperImpl
import com.karim.booksapp.screens.main.repositories.BookRepository
import com.karim.booksapp.screens.main.repositories.BookRepositoryImpl
import com.karim.booksapp.repositories.PermissionRepository
import com.karim.booksapp.network.retrofit.BookRetrofitService
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
 fun provideBookRepository(bookRetrofitService : BookRetrofitService
 ): BookRepository {
  return BookRepositoryImpl(
   bookRetrofitService
  )
 }

 @MainScope
 @Provides
 fun provideFileHelperWrapper(application: Application , permissionRepository : PermissionRepository
 ): FileHelperWrapper {
  return FileHelperWrapperImpl(
   application, permissionRepository
  )
 }


 }
