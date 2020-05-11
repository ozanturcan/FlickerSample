package com.penguinlab.domain

import com.penguinlab.common.Mapper
import com.penguinlab.data.feed.response.FetchRecentResponse
import com.penguinlab.model.PhotoItem
import javax.inject.Inject

class FetchRecentPhotoMapper @Inject constructor() : Mapper<FetchRecentResponse, List<PhotoItem>> {

    override fun mapFrom(type: FetchRecentResponse): List<PhotoItem> {
        return type.photoListResult?.photoList!!.map { itemResponse ->
            PhotoItem(
                farm = itemResponse?.farm,
                id = itemResponse?.id,
                isfamily = itemResponse?.isfamily,
                isfriend = itemResponse?.isfriend,
                ispublic = itemResponse?.ispublic,
                owner = itemResponse?.owner,
                secret = itemResponse?.secret,
                server = itemResponse?.server,
                title = itemResponse?.title
            )
        }
    }
}