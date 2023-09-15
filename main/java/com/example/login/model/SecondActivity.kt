package com.example.login.model


import android.os.Bundle



import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R

import com.example.myapplication.databinding.SecondActivityBinding

import com.google.android.material.bottomnavigation.BottomNavigationView



class SecondActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: SecondActivityBinding

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            binding = SecondActivityBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigaation)
            bottomNavigationView.setupWithNavController(navController)
        }


}