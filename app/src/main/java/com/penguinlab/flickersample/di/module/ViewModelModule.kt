package com.penguinlab.flickersample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.penguinlab.flickersample.di.ViewModelFactory
import com.penguinlab.flickersample.di.key.ViewModelKey
import com.penguinlab.flickersample.ui.MainActivityViewModel
import com.penguinlab.flickersample.ui.getrecent.RecentFeedViewModel
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


    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}