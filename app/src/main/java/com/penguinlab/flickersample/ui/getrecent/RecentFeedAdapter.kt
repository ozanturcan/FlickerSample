package com.penguinlab.flickersample.ui.getrecent

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.penguinlab.common.inflate
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.PhotoItemBinding
import com.penguinlab.model.PhotoItem
import javax.inject.Inject

//todo gereksiz ise inject kaldir

class RecentFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<RecentFeedAdapter.RecentPhotoFeedItemViewHolder>() {

    private var recentPhotoList: MutableList<PhotoItem> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentPhotoFeedItemViewHolder {
        val itemBinding = parent.inflate<PhotoItemBinding>(R.layout.photo_item, false)
        return RecentPhotoFeedItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = recentPhotoList.size

    override fun onBindViewHolder(holder: RecentPhotoFeedItemViewHolder, position: Int) {
        holder.bind(getRecentPhoto(position))
    }

    private fun getRecentPhoto(position: Int) = recentPhotoList[position]

    fun setRecentPhoto(list: List<PhotoItem>) {
        val beforeSize = recentPhotoList.size
        recentPhotoList.addAll(list)
        notifyItemRangeInserted(beforeSize, list.size)
    }

    inner class RecentPhotoFeedItemViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photoItem: PhotoItem) {
            with(binding) {
                viewState = RecentPhotoItemViewState(photoItem)
                executePendingBindings()
            }
        }

    }
}