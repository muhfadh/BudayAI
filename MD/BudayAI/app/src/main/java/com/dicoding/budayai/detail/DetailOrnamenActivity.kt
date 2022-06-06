package com.dicoding.budayai.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.R
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.databinding.ActivityDetailOrnamenBinding
import com.dicoding.budayai.detail.adapter.PageAdapter
import com.dicoding.budayai.home.HomeFragment

class DetailOrnamenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOrnamenBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailOrnamenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = PageAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val data = intent.getParcelableExtra<ResponseClassItem>(HomeFragment.DETAIL)
        val tvvAppBar = findViewById<TextView>(R.id.tv_app_bar)

        Glide.with(this)
            .load(data?.photoUrl)
            .apply(RequestOptions().override(300,300))
            .into(binding.imgObjekDetail)
        with(this) {
            binding.apply {
                tvvAppBar.text = "Detail " + data?.type
            }
        }
    }

    companion object {
        val DETAIL = "DETAIL"
    }
}