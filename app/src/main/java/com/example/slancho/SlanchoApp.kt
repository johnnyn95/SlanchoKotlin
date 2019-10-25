package com.example.slancho

import android.app.Application
import android.content.Context
import com.example.slancho.di.AppComponent
import com.example.slancho.di.AppInjector
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import javax.inject.Inject

class SlanchoApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    companion object {
        fun getAppComponent(context: Context) =
            (context.applicationContext as SlanchoApp).appComponent
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        appComponent = AppInjector.init(this)
        FirebaseApp.initializeApp(this)
        Timber.plant(
            if (BuildConfig.DEBUG) {
                Timber.DebugTree()
            } else {
                Timber.asTree()
            }
        )
    }

    override fun androidInjector(): AndroidInjector<Any> =
        dispatchingAndroidInjector

}