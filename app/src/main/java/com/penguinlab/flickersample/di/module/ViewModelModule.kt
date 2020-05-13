package com.penguinlab.flickersample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.penguinlab.flickersample.di.ViewModelFactory
import com.penguinlab.flickersample.di.key.ViewModelKey
import com.penguinlab.flickersample.ui.MainActivityViewModel
import com.penguinlab.flickersample.ui.detail.ImageDetailViewModel
import com.penguinlab.flickersample.ui.getrecent.RecentFeedViewModel
import com.penguinlab.flickersample.ui.splash.SplashActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(RecentFeedViewModel::class)
    fun provideRecentFeedViewModel(recentFeedViewModel: RecentFeedViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ImageDetailViewModel::class)
    fun provideImageDetailViewModel(imageDetailViewModel: ImageDetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SplashActivityViewModel::class)
    fun provideSplashActivityViewModel(splashActivityViewModel: SplashActivityViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}