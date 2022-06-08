package com.dicoding.budayai.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.ActivityLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginFragment.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.fragment_login_popup, null)

            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()

            dialog.findViewById<View>(R.id.btn_login)?.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
//                dialog.dismiss()
            }
        }
    }
}