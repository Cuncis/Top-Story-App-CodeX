package com.reksa.topstoryappcodex.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reksa.topstoryappcodex.R
import com.reksa.topstoryappcodex.model.StoryResponse
import kotlinx.android.synthetic.main.item_top_stories.view.*

@SuppressLint("SetTextI18n")
class TopStoryAdapter(private val context: Context): RecyclerView.Adapter<TopStoryAdapter.TopStoryHolder>() {

    private var topStories: ArrayList<StoryResponse> = ArrayList()
    private lateinit var detailClickListener: DetailClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoryHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_top_stories, parent, false)
        return TopStoryHolder(view)
    }

    override fun getItemCount(): Int {
        return topStories.size
    }

    override fun onBindViewHolder(holder: TopStoryHolder, position: Int) {
        holder.bind(topStories[position])
    }

    fun setTopStories(list: List<StoryResponse>) {
        topStories.addAll(list)
        notifyDataSetChanged()
    }

    fun setDetailClickListener(detailClickListener: DetailClickListener) {
        this.detailClickListener = detailClickListener
    }

    inner class TopStoryHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var judul: TextView = view.tv_judul
        var jmlKomen: TextView = view.tv_jmlKomen
        var score: TextView = view.tv_score

        fun bind(data: StoryResponse) {
            judul.text = data.title
            jmlKomen.text = "${data.descendants} comments"
            score.text = "Score ${data.score}"

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            detailClickListener.onDetailClick(adapterPosition)
        }
    }

    interface DetailClickListener {
        fun onDetailClick(position: Int)
    }
}