package com.reksa.topstoryappcodex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.reksa.topstoryappcodex.R
import com.reksa.topstoryappcodex.model.StoryResponse
import com.reksa.topstoryappcodex.ui.detail.StoryDetailActivity
import com.reksa.topstoryappcodex.util.Constant.SIZE_OF_DATA
import com.reksa.topstoryappcodex.util.Utility
import kotlinx.android.synthetic.main.activity_top_story_list.*

class TopStoryListActivity : AppCompatActivity(), TopStoryListContract.View, TopStoryAdapter.DetailClickListener {

    private var list = arrayListOf<Int>()
    private var topStories = arrayListOf<StoryResponse>()

    private lateinit var topStoryAdapter: TopStoryAdapter
    private lateinit var presenter: TopStoryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_story_list)
        presenter = TopStoryListPresenter(this)
        presenter.getTopStoriesId()
    }

    override fun initRecyclerView() {
        topStoryAdapter = TopStoryAdapter(this)
        topStoryAdapter.setDetailClickListener(this)
        rv_topStories.apply {
            layoutManager = LinearLayoutManager(this@TopStoryListActivity)
            adapter = topStoryAdapter
        }
    }

    override fun onResultTopStoriesId(response: List<Int>) {
        list.addAll(response)
        Utility.showLog("Data Id: $list")
        for (i in 0 until SIZE_OF_DATA) {
            presenter.getTopStories(list[i].toString())
        }
    }

    override fun onResultTopStories(response: StoryResponse) {
        topStories.add(response)
        if (topStories.size == SIZE_OF_DATA) {
            topStoryAdapter.setTopStories(topStories)
            Utility.showLog("Size: ${topStories.size}")
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress_topStories.visibility = View.VISIBLE
            }
            false -> {
                progress_topStories.visibility = View.GONE
            }
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDetailClick(position: Int) {
        val intent = Intent(this, StoryDetailActivity::class.java)
        intent.putExtra("STORY_ID", topStories[position].id)
        startActivity(intent)
    }
}
