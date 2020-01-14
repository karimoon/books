package com.karim.booksapp.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.karim.booksapp.R
import com.karim.booksapp.data.recievers.ConnectivityReceiver
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.main_activity.*

open class BaseActivity : DaggerAppCompatActivity() {

    private var snackBar: Snackbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(container, "You are offline", Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
        } else {
            snackBar?.dismiss()
        }
    }
}
