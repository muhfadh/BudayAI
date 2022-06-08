package com.dicoding.budayai.location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.location_menu -> {
                val intentActivity = Intent(this, LocationActivity::class.java)
                startActivity(intentActivity)
            }
        }
        return super.onOptionsItemSelected(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.location_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}