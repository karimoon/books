package com.karim.booksapp


import android.os.Bundle
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.karim.booksapp.R
import dagger.android.support.DaggerAppCompatActivity
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
