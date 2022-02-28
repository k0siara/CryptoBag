package com.patrykkosieradzki.cryptobag.common.ui.imageloading

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.patrykkosieradzki.cryptobag.utils.android.AppInitializer
import okhttp3.OkHttpClient

class CoilAppInitializer(
    private val okHttpClient: OkHttpClient
) : AppInitializer {
    override fun init(application: Application) {
        val coilOkHttpClient = okHttpClient.newBuilder()
            .cache(CoilUtils.createDefaultCache(application.applicationContext))
            .build()

        Coil.setImageLoader {
            ImageLoader.Builder(application)
                .componentRegistry {
                    add(SvgDecoder(application.applicationContext))
                }
                .okHttpClient(coilOkHttpClient)
                .build()
        }
    }
}
