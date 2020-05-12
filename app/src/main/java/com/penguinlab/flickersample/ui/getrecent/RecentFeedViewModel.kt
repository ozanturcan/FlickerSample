package com.penguinlab.flickersample.ui.getrecent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.penguinlab.common.*
import com.penguinlab.domain.FetchRecentPhotoUseCase
import com.penguinlab.model.PhotoItem
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject


class RecentFeedViewModel @Inject constructor(private val fetchRecentPhotoUseCase: FetchRecentPhotoUseCase) :
    RxAwareViewModel() {


    private val contents = MutableLiveData<List<PhotoItem>>()
    val contents_: LiveData<List<PhotoItem>> = contents

    private val status = MutableLiveData<RecentPhotoStatusViewState>()
    val status_: LiveData<RecentPhotoStatusViewState> = status


    fun fetchRecentPhoto(page: Int) {
        fetchRecentPhotoUseCase
            .fetchRecentPhoto(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                Timber.d(list.toString())
                onRecentPhotoContentResultReady(list)

            }
            .subscribe({ resource ->
                Timber.d(resource.toString())
                onRecentPhotoStatusResultReady(resource)
            }, {})
            .also { disposable += it }
    }

    private fun onRecentPhotoStatusResultReady(resource: Resource<List<PhotoItem>>) {

        val viewState = when (resource) {
            is Resource.Success -> RecentPhotoStatusViewState(Status.Content)
            is Resource.Error -> RecentPhotoStatusViewState(Status.Error(resource.exception))
            Resource.Loading -> RecentPhotoStatusViewState(Status.Loading)
        }
        status.value = viewState
    }

    private fun onRecentPhotoContentResultReady(results: List<PhotoItem>) {
        contents.value = results
    }
}