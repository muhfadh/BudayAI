package com.dicoding.budayai.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.budayai.R
import com.dicoding.budayai.api.response.ResponseClassItem

var sejarahFilter = ArrayList<ResponseClassItem>()

class SejarahAdapter(private val data: ArrayList<ResponseClassItem>) : RecyclerView.Adapter<SejarahAdapter.RecyclerViewHolder>() {
    init {
        sejarahFilter = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SejarahAdapter.RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_sejarah, parent, false)
        mContext = parent.context
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SejarahAdapter.RecyclerViewHolder, position: Int) {
        val data = sejarahFilter[position]
        with(holder){
            tvSejarah.text = data.history
        }
    }

    override fun getItemCount(): Int = sejarahFilter.size

    inner class RecyclerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvSejarah: TextView = itemView.findViewById(R.id.tv_sejarah)
    }
}