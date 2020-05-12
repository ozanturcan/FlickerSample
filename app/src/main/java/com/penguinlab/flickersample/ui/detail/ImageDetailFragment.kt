package com.penguinlab.flickersample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.ImageDetailFragmentBinding
import com.penguinlab.flickersample.ui.base.BaseFragment

class ImageDetailFragment : BaseFragment<ImageDetailFragmentBinding, ImageDetailViewModel>() {

    override val viewModelClass: Class<ImageDetailViewModel> = ImageDetailViewModel::class.java
    override val layoutRes: Int = R.layout.image_detail_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getBundleArguments()
        return binding.root
    }

    private fun renderImage(staticUrl: String) {
        binding.staticUrl = staticUrl
        binding.executePendingBindings()
    }

    private fun getBundleArguments() {
        arguments?.getString("PhotoUrlObject")?.let {
            renderImage(it)
        }
    }
}
