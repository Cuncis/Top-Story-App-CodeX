package com.reksa.topstoryappcodex.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@SerializedName("parent")
	val parent: Int? = null,

	@SerializedName("by")
	val by: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("text")
	val text: String? = null,

	@SerializedName("time")
	val time: Int? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("kids")
	val kids: List<Int>? = null
)
