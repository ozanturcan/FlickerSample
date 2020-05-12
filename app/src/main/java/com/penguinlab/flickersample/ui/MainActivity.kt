package com.penguinlab.flickersample.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.penguinlab.flickersample.R
import com.penguinlab.flickersample.databinding.ActivityMainBinding
import com.penguinlab.flickersample.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val viewModelClass: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override val layoutRes: Int = R.layout.activity_main
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavController()


    }

    private fun setNavController() {
        navController = findNavController(R.id.nav_container)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}