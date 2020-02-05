package com.karim.booksapp.screens.detail

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.karim.booksapp.R
import com.karim.booksapp.models.Book
import com.karim.booksapp.recievers.ConnectivityReceiver
import com.karim.booksapp.databinding.ActivityDetailBookBinding
import com.karim.booksapp.BaseActivity
import com.karim.booksapp.screens.detail.viewmodels.DetailViewModel
import com.karim.booksapp.utils.LOG_TAG
import kotlinx.android.synthetic.main.favorite_buttons.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailBookActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {


    var isRegistered = false

    private var book : Book? = null

    val reciever = ConnectivityReceiver()

    lateinit var viewmodel : DetailViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBookBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_book)

        Log.d(LOG_TAG,providerFactory.toString() )

        viewmodel = ViewModelProviders.of(this,providerFactory).get(DetailViewModel::class.java)

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

        book?.let {
            viewmodel.removeFromFavorites(it) }

        updateFavoriteButtons()
    }

    suspend fun markAsFavorite() {

        book?.let {
            viewmodel.markAsFavorite(it) }

        updateFavoriteButtons()
    }

     suspend fun isFavorite(): Boolean {
         if(book != null) {
             return viewmodel.isFavorite(book!!)
         }
         else {
             return false
         }
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
