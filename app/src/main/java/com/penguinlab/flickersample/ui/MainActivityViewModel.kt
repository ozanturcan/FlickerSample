package com.penguinlab.flickersample.ui

import com.penguinlab.common.RxAwareViewModel
import com.penguinlab.common.doOnSuccess
import com.penguinlab.common.plusAssign
import com.penguinlab.domain.FetchRecentPhotoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val fetchRecentPhotoUseCase: FetchRecentPhotoUseCase) :
    RxAwareViewModel() {

    fun fetchRecentPhoto(page: Int) {
        fetchRecentPhotoUseCase
            .fetchRecentPhoto(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                Timber.d(list.toString())
            }
            .subscribe({ resource ->
                Timber.d(resource.toString())
            }, {})
            .also { disposable += it }
    }


}
