package com.dicoding.budayai.listKatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.budayai.databinding.ActivityListKatalogBinding

class ListKatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}