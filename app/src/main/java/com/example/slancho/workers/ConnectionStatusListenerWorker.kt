package com.example.slancho.workers

import android.content.Context
import androidx.annotation.NonNull
import androidx.work.*
import com.example.slancho.SlanchoApp
import com.example.slancho.utils.network.NetworkManager
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConnectionStatusListenerWorker @Inject constructor(
    @NonNull context: Context,
    @NonNull workerParameters: WorkerParameters
) :
    Worker(context, workerParameters) {

    @Inject
    lateinit var networkManager: NetworkManager

    companion object {
        private fun bindConstraints() =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        fun createConnectionStatusListenerWorker(): OneTimeWorkRequest {
            Timber.d("Connection status listener worker created")
            return OneTimeWorkRequest.Builder(ConnectionStatusListenerWorker::class.java)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.MINUTES)
                .setConstraints(bindConstraints())
                .build()
        }

    }

    override fun doWork(): Result {
        SlanchoApp.getAppComponent(applicationContext).inject(this)
        networkManager.connectionEstablishedListener.connectionEstablished()
        return Result.success()
    }

}