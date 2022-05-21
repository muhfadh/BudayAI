package com.dicoding.budayai.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeToolbar.inflateMenu(R.menu.profile_menu)

        binding.homeToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.profile_menu -> {
                    startActivity(Intent(activity, MainActivity::class.java))
                    //ini nanti ganti ke halaman profile
                }
            }
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}