package com.chilik1020.mustachepawsfp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chilik1020.framework.di.ApplicationScope
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import toothpick.ktp.KTP

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.loginFragment)
    }
}