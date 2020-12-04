package com.chilik1020.mustachepawsfp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }
}