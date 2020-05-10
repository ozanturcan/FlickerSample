package com.penguinlab.flickersample.ui

import android.os.Bundle
import com.penguinlab.common.runIfNull
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.ActivityMainBinding
import com.penguinlab.flickersample.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val viewModelClass: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.runIfNull {
        }
    }


}