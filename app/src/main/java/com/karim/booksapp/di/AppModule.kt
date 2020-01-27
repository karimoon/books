

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.karim.booksapp.API_URL
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.database.BookDatabase
import com.karim.booksapp.data.repository.BookDbRepository
import com.karim.booksapp.di.ui.main.MainScope
import com.karim.booksapp.utilities.SpUtil.Companion.PREF_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSharedPrefsEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Singleton
    @Provides
    fun provideStringInstance(): String {
        return "karim__test"
    }

    @Singleton
    @Provides
    fun provideBookDatabase(app: Application): BookDatabase {
        return BookDatabase.getDatabase(app)
    }

    @Singleton
    @Provides
    fun provideBookDao(db: BookDatabase): BookDao {
        return db.bookDao()
    }

    @Singleton
    @Provides
    fun provideBookDbRepository(
        bookDao : BookDao
    ): BookDbRepository {
        return BookDbRepository(
            bookDao
        )
    }


}