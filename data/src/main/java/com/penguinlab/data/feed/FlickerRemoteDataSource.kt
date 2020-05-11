package com.penguinlab.data.feed

import com.penguinlab.data.remote.FlickerSampleRestInterface
import javax.inject.Inject

class FlickerRemoteDataSource @Inject constructor(private val restInterface: FlickerSampleRestInterface) {

    fun fetchRecentPhoto(page: Int) = restInterface.fetchRecentPhoto(page)

}