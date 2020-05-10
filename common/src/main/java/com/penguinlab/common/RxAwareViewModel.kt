package com.penguinlab.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * reference : https://github.com/googlesamples/android-architecture-components
 */
open class RxAwareViewModel : ViewModel() {
    val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}