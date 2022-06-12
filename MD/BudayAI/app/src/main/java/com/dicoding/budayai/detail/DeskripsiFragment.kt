package com.dicoding.budayai.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.budayai.api.adapter.DeskripsiAdapter
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.databinding.FragmentDeskripsiBinding

class DeskripsiFragment : Fragment() {

    private lateinit var binding: FragmentDeskripsiBinding
    private val data: ArrayList<ResponseClassItem> = ArrayList()
    private lateinit var dataAdapter: DeskripsiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeskripsiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAdapter = DeskripsiAdapter(data)
        data.clear()
        val datas = requireActivity().intent.getParcelableExtra<ResponseClassItem>(DeskripsiFragment.DETAIL) as ResponseClassItem

        with(this) {
            binding.apply {
                tvDescription.text = datas.detail
            }
        }
    }

    companion object{
        val DETAIL = "DETAIL"
    }
}