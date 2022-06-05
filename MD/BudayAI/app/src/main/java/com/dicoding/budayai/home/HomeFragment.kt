package com.dicoding.budayai.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.R
import com.dicoding.budayai.api.adapter.ListAdapter
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.databinding.FragmentHomeBinding
import com.dicoding.budayai.viewModel.FactoryModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var factoryModel: FactoryModel
    private val homeModel: HomeModel by activityViewModels{factoryModel}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        factoryModel = FactoryModel.getInstance(requireActivity())
        listAdapter = ListAdapter()

        binding.layoutSwipe.setOnRefreshListener {
            homeModel.getData()
        }

        homeModel.getData()

        binding.rvPopuler.layoutManager = LinearLayoutManager(activity)
        homeModel.data.observe(viewLifecycleOwner){
            listAdapter.setData(it as ArrayList<ResponseClassItem>)
        }

        binding.rvPopuler.adapter = listAdapter

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

        listAdapter.setOnItemClicked(object : ListAdapter.OnItemClicked{
            override fun onItemClicked(data: ResponseClassItem) {
                showSelectedData(data)
            }

        })
    }

    private fun showSelectedData(data: ResponseClassItem){
        ResponseClassItem(
            data.lon,
            data.id,
            data.detail,
            data.history,
            data.photoUrl,
            data.type,
            data.lat
        )
//        val intent = Intent(context, DetailOrnamenActivity::class.java)
//        intent.putExtra(DetailOrnamenActivity.DETAIL, data)
//        this.startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}