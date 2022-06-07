package com.dicoding.budayai

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.budayai.analys.CameraActivity
import com.dicoding.budayai.databinding.ActivityMainBinding
import com.dicoding.budayai.location.LocationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_app")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                ALLOW_PERMISSION,
                CODE_ACCESS_PERMISSION
            )
        }

        binding.bottomNavView.background = null

        val navView = findViewById<BottomNavigationView>(R.id.bottom_navView)

        val navController = findNavController(R.id.fragment_view)
        navView.setupWithNavController(navController)

        binding.fabBottomBar.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            finish()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODE_ACCESS_PERMISSION) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Anda tidak memiliki izin untuk akses",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = ALLOW_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val CODE_ACCESS_PERMISSION = 10
        private val ALLOW_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }
}