package com.dicoding.budayai.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDetect(

	@field:SerializedName("detection_classes")
	val detectionClasses: List<Int>,

	@field:SerializedName("list_categories")
	val listCategories: List<String>,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("detection_scores")
	val detectionScores: List<Double>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
): Parcelable
