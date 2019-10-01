package com.example.slancho

import android.app.Activity
import android.app.Application
import com.example.slancho.di.AppInjector
import com.google.firebase.FirebaseApp
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import javax.inject.Inject

class SlanchoApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        AppInjector.init(this)
        FirebaseApp.initializeApp(this)
        Timber.plant(
            if (BuildConfig.DEBUG) {
                Timber.DebugTree()
            } else {
                Timber.asTree()
            }
        )
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> =
        dispatchingAndroidInjector
}