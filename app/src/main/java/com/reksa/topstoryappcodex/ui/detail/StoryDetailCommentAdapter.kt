package com.reksa.topstoryappcodex.ui.detail

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reksa.topstoryappcodex.R
import com.reksa.topstoryappcodex.model.CommentResponse
import kotlinx.android.synthetic.main.item_comments.view.*

class StoryDetailCommentAdapter(private val context: Context): RecyclerView.Adapter<StoryDetailCommentAdapter.StoryDetailCommentHolder>() {

    private var commentList = ArrayList<CommentResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryDetailCommentHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comments, parent, false)
        return StoryDetailCommentHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: StoryDetailCommentHolder, position: Int) {
        holder.bind(commentList[position])
    }

    fun setCommentList(list: List<CommentResponse>) {
        commentList.addAll(list)
        notifyDataSetChanged()
    }

    inner class StoryDetailCommentHolder(view: View): RecyclerView.ViewHolder(view) {
        var textName: TextView = view.tv_name
        var textComment: TextView = view.tv_comment

        fun bind(data: CommentResponse) {
            textName.text = data.by
            textComment.text = Html.fromHtml(data.text).toString()
        }
    }

}