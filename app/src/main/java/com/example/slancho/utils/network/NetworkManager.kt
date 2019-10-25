package com.example.slancho.utils.network

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import timber.log.Timber
import javax.inject.Inject

class NetworkManager @Inject constructor(var application: Application) :
    ConnectionEstablishedListener {
    companion object {
        const val CONNECTION_LISTENER_WORKER_ID = "connection_listener_worker_id"
    }

    var connectionEstablishedListener: ConnectionEstablishedListener = this

    var connectivityManager: ConnectivityManager =
        application.baseContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    fun hasConnection(): Boolean {
        val hasConnection =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null

        Timber.d("Has connection $hasConnection")
        return hasConnection
    }

    override fun connectionEstablished() {
        Timber.d("Connection Established")
    }
}