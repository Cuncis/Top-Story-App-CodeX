
package com.reksa.topstoryappcodex.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reksa.topstoryappcodex.R
import com.reksa.topstoryappcodex.model.CommentResponse
import com.reksa.topstoryappcodex.model.StoryResponse
import com.reksa.topstoryappcodex.util.Utility
import kotlinx.android.synthetic.main.activity_story_detail.*

class StoryDetailActivity : AppCompatActivity(), StoryDetailContract.View {

    private lateinit var presenter: StoryDetailPresenter
    private lateinit var commentAdapter: StoryDetailCommentAdapter
    private var list = ArrayList<Int>()
    private var comments = arrayListOf<CommentResponse>()
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)
        supportActionBar?.title = "Story Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = StoryDetailPresenter(this)

        if (intent.hasExtra("STORY_ID")) {
            presenter.getTopStoriesDetail(intent.getIntExtra("STORY_ID", 0).toString())
        }
    }

    override fun initRecyclerView() {
        commentAdapter = StoryDetailCommentAdapter(this)
        rv_commentsDetail.apply {
            layoutManager = LinearLayoutManager(this@StoryDetailActivity)
            adapter = commentAdapter
        }
    }

    override fun initListener() {
        tv_descriptionDetail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress_storyDetail.visibility = View.VISIBLE
            }
            false -> {
                progress_storyDetail.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResultDetail(response: StoryResponse) {
        url = response.url!!
        list.addAll(response.kids!!)
        tv_titleDetail.text = response.title
        tv_authorDetail.text = "By ${response.by}"
        tv_dateDetail.text = "${Utility.getDateTimeFromEpocLongOfSeconds(response.time?.toLong()!!)}"
        tv_descriptionDetail.text = response.url

        for (i in 0 until list.size) {
            presenter.getAllCommentById(list[i].toString())
        }
    }

    override fun onResultComment(response: CommentResponse) {
        comments.add(response)
        if (comments.size == list.size) {
            commentAdapter.setCommentList(comments)
            Utility.showLog("Size: ${comments.size}")
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
