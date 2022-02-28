package com.patrykkosieradzki.cryptobag

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CryptoBagApp : Application() {

    @Inject lateinit var initializers: CryptoBagAppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }
}
