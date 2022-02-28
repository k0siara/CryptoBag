package com.patrykkosieradzki.cryptobag.common.ui.compose

import com.patrykkosieradzki.composer.toast.ComposerToastManager
import com.patrykkosieradzki.composer.toast.ToastManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    @Singleton
    fun provideToastManager(): ToastManager {
        return ComposerToastManager()
    }
}
