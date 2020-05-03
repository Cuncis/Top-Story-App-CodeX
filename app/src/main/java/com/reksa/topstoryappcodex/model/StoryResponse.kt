package com.reksa.topstoryappcodex.model

import com.google.gson.annotations.SerializedName

data class StoryResponse(

	@SerializedName("score")
	val score: Int? = null,

	@SerializedName("by")
	val by: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("time")
	val time: Int? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("descendants")
	val descendants: Int? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("kids")
	val kids: List<Int>? = null
)
