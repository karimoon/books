

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.karim.booksapp.utils.API_URL
import com.karim.booksapp.room.BookDao
import com.karim.booksapp.room.BookDatabase
import com.karim.booksapp.network.helpers.NetworkHelperWrapper
import com.karim.booksapp.network.helpers.NetworkHelperWrapperImpl
import com.karim.booksapp.screens.main.repositories.BookDbRepository
import com.karim.booksapp.screens.main.repositories.BookDbRepositoryImpl
import com.karim.booksapp.repositories.PermissionRepository
import com.karim.booksapp.repositories.PermissionRepositoryImpl
import com.karim.booksapp.utils.SpUtil.Companion.PREF_NAME
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
        return BookDbRepositoryImpl(
            bookDao
        )
    }

    @Singleton
    @Provides
    fun provideNetworkHelperWrapper(application: Application
    ): NetworkHelperWrapper {
        return NetworkHelperWrapperImpl(
            application
        )
    }

    @Singleton
    @Provides
    fun providePermissionRepository(application: Application
    ): PermissionRepository {
        return PermissionRepositoryImpl(
            application
        )
    }


}