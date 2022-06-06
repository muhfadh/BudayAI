package com.dicoding.budayai.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.budayai.R
import com.dicoding.budayai.api.response.ResponseClassItem

var deskripsiFilter = ArrayList<ResponseClassItem>()

class DeskripsiAdapter(private val data: ArrayList<ResponseClassItem>) : RecyclerView.Adapter<DeskripsiAdapter.RecyclerViewHolder>() {
    init {
        deskripsiFilter = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeskripsiAdapter.RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_deskripsi, parent, false)
        mContext = parent.context
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeskripsiAdapter.RecyclerViewHolder, position: Int) {
        val data = deskripsiFilter[position]
        with(holder){
            tvDeskripsi.text = data.detail
        }
    }

    override fun getItemCount(): Int = deskripsiFilter.size

    inner class RecyclerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tv_description)
    }
}