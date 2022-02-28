package com.patrykkosieradzki.cryptobag.utils.android

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}