package com.karim.booksapp.ui.detail

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.karim.booksapp.LOG_TAG
import com.karim.booksapp.R
import com.karim.booksapp.data.database.BookDatabase
import com.karim.booksapp.data.models.Book
import com.karim.booksapp.data.recievers.ConnectivityReceiver
import com.karim.booksapp.databinding.ActivityDetailBookBinding
import com.karim.booksapp.ui.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBookActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {


    private lateinit var mButtonMarkAsFavorite: Button
    private lateinit var mButtonRemoveFromFavorites: Button

    var isRegistered = false

    private lateinit var book : Book

    val reciever = ConnectivityReceiver()

    private val bookDao = BookDatabase.getDatabase(this)
        .bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBookBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_book)

        mButtonMarkAsFavorite = findViewById(R.id.button_mark_as_favorite)
        mButtonRemoveFromFavorites = findViewById(R.id.button_remove_from_favorites)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        book = intent.getParcelableExtra<Book>("Book")
        binding.book = book
        CoroutineScope(Dispatchers.IO).launch{updateFavoriteButtons()}

    }

    suspend fun updateFavoriteButtons() {

        mButtonMarkAsFavorite.setOnClickListener { view ->
            CoroutineScope(Dispatchers.IO).launch{markAsFavorite()}
            }

        mButtonRemoveFromFavorites.setOnClickListener { view ->  CoroutineScope(Dispatchers.IO).launch{removeFromFavorites()}}

        val isFavorite = isFavorite()
        if(isFavorite){
            withContext(Main){
                mButtonRemoveFromFavorites.setVisibility(View.VISIBLE)
                mButtonMarkAsFavorite.setVisibility(View.GONE)
            }
        }
        else{
            withContext(Main){
                mButtonMarkAsFavorite.setVisibility(View.VISIBLE)
                mButtonRemoveFromFavorites.setVisibility(View.GONE)
            }
        }
    }

    suspend fun removeFromFavorites() {

        val isFavorite = isFavorite()
        if(isFavorite){
            bookDao.delete(book)
        }
        updateFavoriteButtons()
    }

    suspend fun markAsFavorite() {

        val isFavorite = isFavorite()
        if(!isFavorite){
            bookDao.insertBook(book)
        }
        updateFavoriteButtons()
    }

     suspend fun isFavorite(): Boolean {

         var  bookretrieved = bookDao.findBookById(book.id)
         return bookretrieved != null
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(reciever, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        isRegistered = true
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        if (isRegistered) {
            try {
                unregisterReceiver(reciever)
                isRegistered = false
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
