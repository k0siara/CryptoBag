package com.patrykkosieradzki.cryptobag.common.ui.imageloading

import com.patrykkosieradzki.cryptobag.utils.android.AppInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object ImageLoadingModule {

    @Provides
    @IntoSet
    fun provideCoilAppInitializer(okHttpClient: OkHttpClient): AppInitializer {
        return CoilAppInitializer(okHttpClient)
    }
}