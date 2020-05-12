package com.penguinlab.flickersample.ui.getrecent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.penguinlab.common.observeNonNull
import com.penguinlab.common.runIfNull
import com.penguinlab.common.ui.EndlessScrollListener
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.RecentFeedFragmentBinding
import com.penguinlab.flickersample.ui.base.BaseFragment
import com.penguinlab.model.PhotoItem

class RecentFeedFragment : BaseFragment<RecentFeedFragmentBinding, RecentFeedViewModel>() {

    override val viewModelClass: Class<RecentFeedViewModel> = RecentFeedViewModel::class.java
    override val layoutRes: Int = R.layout.recent_feed_fragment
    private var recentFeedAdapter = RecentFeedAdapter().apply { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.contents_.observeNonNull(this) { contents ->
            renderPopularTVShows(contents)
        }

        viewModel.status_.observeNonNull(this) { contents ->
            renderStatusResult(contents)
        }

        savedInstanceState.runIfNull {
            fetchRecentPhoto(FIRST_PAGE)
        }
        initPopularTVShowsRecyclerView()
        return binding.root

    }

    private fun renderStatusResult(statusViewState: RecentPhotoStatusViewState) {
        binding.viewState = statusViewState
        binding.executePendingBindings()
    }

    private fun initPopularTVShowsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.apply {
            adapter = recentFeedAdapter.apply {
                listener = object : RecentFeedAdapter.ItemClickListener {
                    override fun onItemClick(view: View, photoUrl: String) {
                        findNavController().navigate(
                            R.id.action_recentFeedFragment_to_imageDetailFragment
                            , bundleOf("PhotoUrlObject" to photoUrl)
                        )
                    }
                }
            }
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    fetchRecentPhoto(page)
                }
            })
        }
    }

    private fun renderPopularTVShows(contents: List<PhotoItem>) {
        recentFeedAdapter.setRecentPhoto(contents)
    }

    private fun fetchRecentPhoto(page: Int) {
        viewModel.fetchRecentPhoto(page)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}
