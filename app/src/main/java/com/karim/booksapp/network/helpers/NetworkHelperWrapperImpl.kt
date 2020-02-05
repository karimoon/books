package com.karim.booksapp.network.helpers

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkHelperWrapperImpl
@Inject
constructor(val app: Application) : NetworkHelperWrapper
{

    @Suppress("DEPRECATION")
    override fun networkAvalable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}