package com.dicoding.budayai.api.response

import com.google.gson.annotations.SerializedName

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
)
