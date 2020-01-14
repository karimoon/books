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
import kotlinx.android.synthetic.main.favorite_buttons.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBookActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {


    var isRegistered = false

    private  var book : Book? = null

    val reciever = ConnectivityReceiver()

    private val bookDao = BookDatabase.getDatabase(this)
        .bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBookBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_book)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        book = intent.getParcelableExtra<Book>("Book")
        binding.book = book
        CoroutineScope(Dispatchers.IO).launch{updateFavoriteButtons()}

    }

    suspend fun updateFavoriteButtons() {

        button_mark_as_favorite.setOnClickListener { view ->
            CoroutineScope(Dispatchers.IO).launch{markAsFavorite()}
            }

        button_remove_from_favorites.setOnClickListener { view ->  CoroutineScope(Dispatchers.IO).launch{removeFromFavorites()}}

        val isFavorite = isFavorite()
        if(isFavorite){
            withContext(Main){
                button_remove_from_favorites.setVisibility(View.VISIBLE)
                button_mark_as_favorite.setVisibility(View.GONE)
            }
        }
        else{
            withContext(Main){
                button_mark_as_favorite.setVisibility(View.VISIBLE)
                button_remove_from_favorites.setVisibility(View.GONE)
            }
        }
    }

    suspend fun removeFromFavorites() {

        val isFavorite = isFavorite()
        if(isFavorite){
            book?.let { bookDao.delete(it) }
        }
        updateFavoriteButtons()
    }

    suspend fun markAsFavorite() {

        val isFavorite = isFavorite()
        if(!isFavorite){
            book?.let { bookDao.insertBook(it) }
        }
        updateFavoriteButtons()
    }

     suspend fun isFavorite(): Boolean {

         var  bookretrieved = book?.id?.let { bookDao.findBookById(it) }
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
