package com.penguinlab.data.remote

import com.penguinlab.data.feed.response.FetchRecentResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerSampleRestInterface {

    @GET("rest")
    fun fetchRecentPhoto(
        @Query("page") page: Int,
        @Query("method") method: String? = "flickr.photos.getRecent",
        @Query("format") format: String? = "json",
        @Query("nojsoncallback") noJsonCallback: Int? = 1,
        @Query("per_page") perPage: Int? = 20
    ): Observable<FetchRecentResponse>


}