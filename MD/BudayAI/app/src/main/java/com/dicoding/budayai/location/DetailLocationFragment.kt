package com.dicoding.budayai.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.FragmentDetailLocationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailLocationFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailLocationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = DetailLocationFragmentArgs.fromBundle(arguments as Bundle)
        Glide.with(requireActivity())
            .load(bundle.photoUrl)
            .into(binding.detailMap.avatar)
        binding.detailMap.tvNameOrnamen.text = bundle.type
        binding.detailMap.tvNameOrnamen.text = bundle.detail
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
}