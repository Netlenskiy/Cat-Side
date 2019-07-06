package com.netlenskiy.catside.app

import android.app.Application
import com.netlenskiy.catside.R
import com.netlenskiy.catside.app.modules.NetworkModule
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(getString(R.string.base_url), getString(R.string.api_key)))
            .build()
    }

    companion object {
        private lateinit var app: App

        @Synchronized
        fun getInstance(): App {
            return app
        }
    }
}