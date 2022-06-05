package com.dicoding.budayai.api.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.databinding.ItemObjekBinding
import com.dicoding.budayai.detail.DetailOrnamenActivity
import com.dicoding.budayai.util.Callback

lateinit var mContext: Context
class ListAdapter : RecyclerView.Adapter<ListAdapter.RecyclerViewHolder>(){
    private var arrayListOrnamen = ArrayList<ResponseClassItem>()

    fun setData(updateData: ArrayList<ResponseClassItem>){
        val dataCallback= Callback(arrayListOrnamen, updateData)
        val result= DiffUtil.calculateDiff(dataCallback)

        arrayListOrnamen = updateData
        result.dispatchUpdatesTo(this)
//        arrayListOrnamen.clear()
//        arrayListOrnamen.addAll(data)
//        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.RecyclerViewHolder {
        mContext = parent.context
        return  RecyclerViewHolder(ItemObjekBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val list = arrayListOrnamen[position]
        Glide.with(holder.itemView.context)
            .load(list.photoUrl)
            .apply(RequestOptions().override(210, 210))
            .into(holder.image)
        with(holder){
            name.text = list.type
            type.text = list.type
            itemView.setOnClickListener {
                val data = ResponseClassItem(
                    list.lon,
                    list.id,
                    list.detail,
                    list.history,
                    list.photoUrl,
                    list.type,
                    list.lat
                )
                val intentActivity = Intent(mContext, DetailOrnamenActivity::class.java)
                intentActivity.putExtra(DetailOrnamenActivity.DETAIL, data)
                mContext.startActivity(intentActivity)
            }
        }
    }

    override fun getItemCount(): Int = arrayListOrnamen.size

    inner class RecyclerViewHolder(binding: ItemObjekBinding): RecyclerView.ViewHolder(binding.root){
        var image = binding.imgObjek
        var name = binding.tvName
        var type = binding.tvType
    }

    interface OnItemClicked {
        fun onItemClicked(data: ResponseClassItem)
    }

    private var onItemClicked: OnItemClicked? = null

    fun setOnItemClicked(onItemClicked: OnItemClicked) {
        this.onItemClicked = onItemClicked
    }
}