package com.reksa.topstoryappcodex.data

import com.reksa.topstoryappcodex.model.CommentResponse
import com.reksa.topstoryappcodex.model.StoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheTopStoryApi {

    @GET("/v0/topstories.json")
    fun getTopStoriesId(): Call<List<Int>>

    @GET("/v0/item/{id}.json")
    fun getTopStories(@Path("id") id: String)
            : Call<StoryResponse>

    @GET("/v0/item/{id}.json")
    fun getComments(@Path("id") id: String)
            : Call<CommentResponse>

}