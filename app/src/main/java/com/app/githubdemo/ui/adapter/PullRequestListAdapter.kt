package com.app.githubdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.githubdemo.GithubDemoApp
import com.app.githubdemo.R
import com.app.githubdemo.databinding.ItemPullRequestBinding
import com.app.githubdemo.network.model.PullRequestInfo
import com.app.githubdemo.utils.DateTimeUtils
import com.app.githubdemo.utils.ImageUtils
import javax.inject.Inject

class PullRequestListAdapter(private val pullRequestList: List<PullRequestInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        GithubDemoApp.getAppComponent().inject(this)
    }

    @Inject
    lateinit var imageUtils: ImageUtils

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PullRequestInfoVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pull_request,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PullRequestInfoVH).bind(pullRequestList[position])
    }

    inner class PullRequestInfoVH(
        val binding: ItemPullRequestBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PullRequestInfo) {
            binding.iprTxtTitle.text = item.title
            binding.iprTxtUserName.text = item.user.login
            imageUtils.loadImageWithUrl(item.user.avatarUrl, binding.iprImgUser, -1, -1)
            binding.iprTxtCreated.text = DateTimeUtils.getDateTime(item.createdAt)
            binding.iprTxtClosed.text = DateTimeUtils.getDateTime(item.closedAt)
        }
    }
}