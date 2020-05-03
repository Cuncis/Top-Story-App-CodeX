package com.reksa.topstoryappcodex.ui

import com.reksa.topstoryappcodex.model.StoryResponse

interface TopStoryListContract {

    interface Presenter {
        fun getTopStoriesId()
        fun getTopStories(id: String)
    }

    interface View {
        fun initRecyclerView()
        fun onResultTopStoriesId(response: List<Int>)
        fun onResultTopStories(response: StoryResponse)
        fun onLoading(loading: Boolean)
        fun showMessage(message: String)
    }

}