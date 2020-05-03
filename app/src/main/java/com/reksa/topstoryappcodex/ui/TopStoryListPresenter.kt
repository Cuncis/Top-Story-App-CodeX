package com.reksa.topstoryappcodex.ui

import android.widget.Toast
import com.reksa.topstoryappcodex.data.ApiClient
import com.reksa.topstoryappcodex.model.StoryResponse
import com.reksa.topstoryappcodex.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopStoryListPresenter(private val view: TopStoryListContract.View): TopStoryListContract.Presenter {

    init {
        view.initRecyclerView()
    }

    override fun getTopStoriesId() {
        view.onLoading(true)
        val call = ApiClient.theTopStoryApi.getTopStoriesId()
        call.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    view.onResultTopStoriesId(response.body()!!)
                } else {
                    view.showMessage(response.message())
                }
            }
            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                view.onLoading(false)
                Utility.showLog("Error: ${t.message}")
            }
        })
    }

    override fun getTopStories(id: String) {
        view.onLoading(true)
        val call = ApiClient.theTopStoryApi.getTopStories(id)
        call.enqueue(object : Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    view.onResultTopStories(response.body()!!)
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

}