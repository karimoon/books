package com.karim.booksapp.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.karim.booksapp.LOG_TAG
import com.karim.booksapp.R
import com.karim.booksapp.ViewModelProviderFactory
import com.karim.booksapp.data.recievers.ConnectivityReceiver
import com.karim.booksapp.ui.main.BookListFragment
import com.karim.booksapp.ui.main.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    var isRegistered = false
    val reciever = ConnectivityReceiver()
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    @Inject
    lateinit var textex : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Log.d(LOG_TAG, "providerString-->" + textex)
        Log.d(LOG_TAG, "providerString-->" + providerFactory)

        viewModel = ViewModelProviders.of(this,providerFactory).get(MainViewModel::class.java)

        viewModel.isNetworkAvailable.observe(this, Observer {
            showNetworkMessage(it)
        })
    }

    override fun onResume() {
        super.onResume()

        registerReceiver(reciever, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        isRegistered = true

        ConnectivityReceiver.connectivityReceiverListener = this

        viewModel.isNetworkAvailable?.value?.let {
            if(!it){
                showNetworkMessage(it)
            }
        }

        if(!viewModel.isNetworkAvailable()){
            showNetworkMessage(false)
        }

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
        viewModel.isNetworkAvailable.value = isConnected

        //showNetworkMessage(isConnected)
    }

    override fun onBackPressed() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        val currentFragment = navHostFragment?.getChildFragmentManager()?.fragments?.get(0)

        if(!viewModel.isNetworkAvailable() && currentFragment!!::class.simpleName == BookListFragment::class.simpleName ){

            showNetworkFailureToast()
        }
        else{
            super.onBackPressed()
        }
    }

    fun showNetworkFailureToast(){
        Toast.makeText(this,"Network is not available" , Toast.LENGTH_SHORT ).show()
    }

}
