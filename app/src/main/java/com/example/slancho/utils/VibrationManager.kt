package com.example.slancho.utils

import android.app.Application
import android.content.Context
import android.os.Vibrator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VibrationManager @Inject
constructor(private val application: Application) {
    lateinit var vibrator: Vibrator

    private val failVibration: Long = 50

    fun failVibration() {
        vibrator = application.applicationContext
            .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(failVibration)
    }

}
