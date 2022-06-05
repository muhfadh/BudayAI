package com.dicoding.budayai.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseHomeItem(

	@field:SerializedName("ethnic_name")
	val ethnicName: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("type")
	val type: String
): Parcelable
