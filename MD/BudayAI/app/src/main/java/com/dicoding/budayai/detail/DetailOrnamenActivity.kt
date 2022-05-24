package com.dicoding.budayai.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.budayai.databinding.ActivityDetailOrnamenBinding
import com.dicoding.budayai.detail.adapter.PageAdapter

class DetailOrnamenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOrnamenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailOrnamenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = PageAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}