package com.reksa.topstoryappcodex.ui

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reksa.topstoryappcodex.R
import com.reksa.topstoryappcodex.model.StoryResponse
import com.reksa.topstoryappcodex.ui.detail.StoryDetailActivity
import com.reksa.topstoryappcodex.util.Constant.EXTRA_FAVORITE
import com.reksa.topstoryappcodex.util.Constant.SIZE_OF_DATA
import com.reksa.topstoryappcodex.util.Utility
import kotlinx.android.synthetic.main.activity_top_story_list.*
import java.util.*

class TopStoryListActivity : AppCompatActivity(), TopStoryListContract.View, TopStoryAdapter.DetailClickListener {

    private var list = arrayListOf<Int>()
    private var topStories = arrayListOf<StoryResponse>()
    private var listFavorite = arrayListOf<String>()

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

    fun dialogFavorite() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Favorite Story")
        builder.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, listFavorite)) { dialog, which ->
            showMessage(" ${listFavorite[which]}")
            dialog.dismiss()
        }
        builder.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_more) {
            dialogFavorite()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDetailClick(position: Int) {
        val intent = Intent(this, StoryDetailActivity::class.java)
        intent.putExtra("STORY_ID", topStories[position].id)
        startActivityForResult(intent, EXTRA_FAVORITE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EXTRA_FAVORITE && resultCode == Activity.RESULT_OK) {
            val strData = data?.getStringExtra("RESULT")
            listFavorite.add(strData!!)
            Utility.showLog("Gas $listFavorite")
        }
    }
}
