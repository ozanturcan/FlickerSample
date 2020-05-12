package com.penguinlab.flickersample.ui.getrecent

import com.penguinlab.common.Status


class RecentPhotoStatusViewState(
    val status: Status
) {
    fun isLoading() = status is Status.Loading

    fun getErrorMessage() = if (status is Status.Error) status.exception.message else ""

    fun shouldShowErrorMessage() = status is Status.Error
}