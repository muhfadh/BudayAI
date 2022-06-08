package com.dicoding.budayai.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.databinding.FragmentLoginPopupBinding

class LoginPopupFragment : Fragment() {

    private lateinit var binding: FragmentLoginPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }
}