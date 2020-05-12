package com.penguinlab.flickersample.ui.getrecent

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.penguinlab.common.inflate
import com.penguinlab.common.ui.Util
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.PhotoItemBinding
import com.penguinlab.model.PhotoItem


class RecentFeedAdapter : RecyclerView.Adapter<RecentFeedAdapter.RecentPhotoFeedItemViewHolder>() {

    private var recentPhotoList: MutableList<PhotoItem> = mutableListOf()
    lateinit var listener: ItemClickListener
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
                cardView.setOnClickListener {
                    listener.onItemClick(it, Util.ConvertStaticPhotoUrl(photoItem))
                }
                executePendingBindings()
            }
        }

    }

    interface ItemClickListener {
        fun onItemClick(view: View, photoUrl: String)
    }
}