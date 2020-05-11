package com.penguinlab.data.feed

import com.penguinlab.common.Resource
import com.penguinlab.data.feed.response.FetchRecentResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FlickerRepository @Inject constructor(private val remoteDataSource: FlickerRemoteDataSource) {

    fun fetchRecentPhoto(page: Int): Observable<Resource<FetchRecentResponse>> {
        return remoteDataSource
            .fetchRecentPhoto(page)
            .map<Resource<FetchRecentResponse>> {
                Resource.Success(it)
            }.onErrorReturn { throwable ->
                Resource.Error(throwable)
            }.subscribeOn(Schedulers.io())
    }
}