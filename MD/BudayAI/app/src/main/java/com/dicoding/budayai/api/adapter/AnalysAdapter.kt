package com.dicoding.budayai.api.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.budayai.analys.AnalysFragmentDirections
import com.dicoding.budayai.api.response.ResponseDetect
import com.dicoding.budayai.databinding.ItemSukuBinding
import com.dicoding.budayai.util.CallbackDetect

lateinit var mContexttt: Context

class AnalysAdapter : RecyclerView.Adapter<AnalysAdapter.RecyclerViewHolder>(){
    private var arrayListAnalys = listOf<ResponseDetect>()

    fun setAnalys(updateData: List<ResponseDetect>){
        val dataCallback = CallbackDetect(arrayListAnalys, updateData)
        val result= DiffUtil.calculateDiff(dataCallback)

        arrayListAnalys = updateData
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = arrayListAnalys.size

    inner class RecyclerViewHolder(binding: ItemSukuBinding): RecyclerView.ViewHolder(binding.root){
        var root = binding.root
        var image = binding.avatar
        var name_categories = binding.tvNameOrnamen
        var scores = binding.tvDescription
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnalysAdapter.RecyclerViewHolder {
        mContexttt = parent.context
        return RecyclerViewHolder(ItemSukuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnalysAdapter.RecyclerViewHolder, position: Int) {
        val list = arrayListAnalys[position]
        Glide.with(holder.itemView.context)
            .load(list.imageUrl)
            .apply(RequestOptions().override(300, 300))
            .into(holder.image)
        with(holder){
            name_categories.text = list.listCategories[0]
            scores.text = "Detect Score: " +list.detectionScores[0].toString()

            image.setOnClickListener {
                val toDetailImageFragment = AnalysFragmentDirections.actionNavigationAnalysToDetailImageAnalysFragment(list?.imageUrl)
                val extras = FragmentNavigatorExtras(image to "detail_image")
                root.findNavController().navigate(toDetailImageFragment, extras)
            }
        }
    }
}