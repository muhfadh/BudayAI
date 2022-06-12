package com.dicoding.budayai.analys

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.dicoding.budayai.databinding.FragmentDetailImageAnalysBinding

class DetailImageAnalysFragment : DialogFragment() {
    private lateinit var binding: FragmentDetailImageAnalysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailImageAnalysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val bundle = DetailImageAnalysFragmentArgs.fromBundle(arguments as Bundle)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )

        Glide.with(requireActivity())
            .load(bundle.imageUrl)
            .fitCenter()
            .into(binding.detailImage)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }
}