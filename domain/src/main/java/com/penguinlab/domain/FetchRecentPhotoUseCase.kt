package com.penguinlab.domain

import com.penguinlab.common.Resource
import com.penguinlab.common.map
import com.penguinlab.data.feed.FlickerRepository
import com.penguinlab.model.PhotoItem
import io.reactivex.Observable
import javax.inject.Inject

class FetchRecentPhotoUseCase @Inject constructor(
    private val repository: FlickerRepository,
    private val mapper: FetchRecentPhotoMapper
) {

    fun fetchRecentPhoto(page: Int): Observable<Resource<List<PhotoItem>>> {
        return repository
            .fetchRecentPhoto(page)
            .map { resource ->
                resource.map { response ->
                    mapper.mapFrom(response)
                }
            }.startWith(Resource.Loading)
    }
}