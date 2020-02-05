package com.karim.booksapp.screens.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.karim.booksapp.R
import com.karim.booksapp.recievers.ConnectivityReceiver
import com.karim.booksapp.BaseActivity
import com.karim.booksapp.screens.main.fragments.BookListFragment
import com.karim.booksapp.screens.main.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    var isRegistered = false
    val reciever = ConnectivityReceiver()
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

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
