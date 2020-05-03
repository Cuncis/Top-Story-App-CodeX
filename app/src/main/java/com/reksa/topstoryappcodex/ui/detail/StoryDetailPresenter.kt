package com.reksa.topstoryappcodex.ui.detail

import com.reksa.topstoryappcodex.data.ApiClient
import com.reksa.topstoryappcodex.model.CommentResponse
import com.reksa.topstoryappcodex.model.StoryResponse
import com.reksa.topstoryappcodex.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryDetailPresenter(private val view: StoryDetailContract.View): StoryDetailContract.Presenter {

    init {
        view.initRecyclerView()
        view.initListener()
    }

    override fun getTopStoriesDetail(id: String) {
        view.onLoading(true)
        val call = ApiClient.theTopStoryApi.getTopStories(id)
        call.enqueue(object : Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    view.onResultDetail(response.body()!!)
                } else {
                    view.showMessage(response.message())
                }
            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                view.onLoading(false)
                view.showMessage("${t.message}")
                Utility.showLog("Error: ${t.message}")
            }
        })
    }

    override fun getAllCommentById(id: String) {
        view.onLoading(true)
        val call = ApiClient.theTopStoryApi.getComments(id)
        call.enqueue(object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    view.onResultComment(response.body()!!)
                } else {
                    view.showMessage(response.message())
                }
            }
            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                view.onLoading(false)
                view.showMessage("${t.message}")
                Utility.showLog("Error: ${t.message}")
            }
        })
    }
}