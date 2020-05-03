package com.reksa.topstoryappcodex.ui.detail

import com.reksa.topstoryappcodex.model.CommentResponse
import com.reksa.topstoryappcodex.model.StoryResponse

interface StoryDetailContract {

    interface Presenter {
        fun getTopStoriesDetail(id: String)
        fun getAllCommentById(id: String)
    }

    interface View {
        fun initRecyclerView()
        fun onLoading(loading: Boolean)
        fun onResultDetail(response: StoryResponse)
        fun onResultComment(response: CommentResponse)
        fun showMessage(message: String)
    }

}