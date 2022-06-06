package com.dicoding.budayai.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.budayai.api.adapter.SejarahAdapter
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.databinding.FragmentSejarahBinding

class SejarahFragment : Fragment() {

    private lateinit var binding: FragmentSejarahBinding
    private val data: ArrayList<ResponseClassItem> = ArrayList()
    private lateinit var dataAdapter: SejarahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSejarahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAdapter = SejarahAdapter(data)
        data.clear()
        val datas = requireActivity().intent.getParcelableExtra<ResponseClassItem>(DETAIL) as ResponseClassItem

        with(this) {
            binding.apply {
                tvSejarah.text = datas.history
            }
        }
    }

    companion object{
        val DETAIL = "DETAIL"
    }
}