package com.dicoding.budayai.util

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.budayai.api.response.ResponseDetect

class CallbackDetect (private val lastList: List<ResponseDetect>, private val firstList: List<ResponseDetect>) : DiffUtil.Callback(){

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = when{
        lastList[oldItemPosition].imageUrl != firstList[newItemPosition].imageUrl -> false
        else -> true
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = lastList[oldItemPosition].imageUrl == firstList[newItemPosition].imageUrl

    override fun getNewListSize(): Int = lastList.size

    override fun getOldListSize(): Int = firstList.size
}