package com.dicoding.budayai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.budayai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(1).isEnabled = false
    }
}