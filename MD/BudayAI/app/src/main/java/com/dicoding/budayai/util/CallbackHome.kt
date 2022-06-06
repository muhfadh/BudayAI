package com.dicoding.budayai.util

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.budayai.api.response.ResponseHomeItem

class CallbackHome (private val lastList: ArrayList<ResponseHomeItem>, private val firstList: ArrayList<ResponseHomeItem>) : DiffUtil.Callback(){

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = when{
        lastList[oldItemPosition].id != firstList[newItemPosition].id -> false
        else -> true
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = lastList[oldItemPosition].id == firstList[newItemPosition].id

    override fun getNewListSize(): Int = lastList.size

    override fun getOldListSize(): Int = firstList.size
}