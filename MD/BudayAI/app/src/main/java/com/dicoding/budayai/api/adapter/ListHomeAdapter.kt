package com.dicoding.budayai.api.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.budayai.api.response.ResponseHomeItem
import com.dicoding.budayai.databinding.ItemObjekBinding
import com.dicoding.budayai.detail.DetailOrnamenActivity
import com.dicoding.budayai.util.CallbackHome

lateinit var mContextt: Context
class ListHomeAdapter : RecyclerView.Adapter<ListHomeAdapter.RecyclerViewHolder>(){
    private var arrayListHome = ArrayList<ResponseHomeItem>()

    fun setDataHome(updateData: ArrayList<ResponseHomeItem>){
        val dataCallback = CallbackHome(arrayListHome, updateData)
        val result= DiffUtil.calculateDiff(dataCallback)

        arrayListHome = updateData
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = arrayListHome.size

    inner class RecyclerViewHolder(binding: ItemObjekBinding): RecyclerView.ViewHolder(binding.root){
        var image = binding.imgObjek
        var name = binding.tvName
        var type = binding.tvType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHomeAdapter.RecyclerViewHolder {
        mContextt = parent.context
        return RecyclerViewHolder(ItemObjekBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListHomeAdapter.RecyclerViewHolder, position: Int) {
        val list = arrayListHome[position]
        Glide.with(holder.itemView.context)
            .load(list.photoUrl)
            .apply(RequestOptions().override(300, 300))
            .into(holder.image)
        with(holder){
            name.text = "Suku " + list.ethnicName
            type.text = list.type
        }
    }
}