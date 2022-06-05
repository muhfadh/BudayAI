package com.dicoding.budayai.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseClassItem(
	val lon: Double,
	val id: String,
	val detail: String,
	val history: String,
	val photoUrl: String,
	val type: String,
	val lat: Double
): Parcelable

