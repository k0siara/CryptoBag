package com.patrykkosieradzki.cryptobag

import android.app.Application
import com.patrykkosieradzki.cryptobag.utils.android.AppInitializer
import javax.inject.Inject

class CryptoBagAppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}