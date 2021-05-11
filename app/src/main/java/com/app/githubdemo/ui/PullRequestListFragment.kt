package com.app.githubdemo.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubdemo.R
import com.app.githubdemo.base.BaseFragment
import com.app.githubdemo.databinding.FragmentListPullRequestBinding
import com.app.githubdemo.network.model.PullRequestInfo
import com.app.githubdemo.ui.adapter.PullRequestListAdapter
import com.app.githubdemo.viewmodel.PullRequestListViewModel

class PullRequestListFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_list_pull_request
    }

    override fun getViewModelClass(): Class<out ViewModel> {
        return PullRequestListViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return null
    }

    companion object {
        fun getNewInstance(): PullRequestListFragment {
            val fragment = PullRequestListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentListPullRequestBinding
    private lateinit var viewModel: PullRequestListViewModel
    private var adapter: PullRequestListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private fun initBindingAndViewModel() {
        binding = getBinding() as FragmentListPullRequestBinding
        viewModel = getViewModel() as PullRequestListViewModel
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingAndViewModel()
        initUI()
        if (savedInstanceState == null) {
            initObservers()
        }
    }

    private fun initUI() {
        binding.rvPullRequests.layoutManager = LinearLayoutManager(activity)
        viewModel.getPullRequestsFromApi(owner = "great799", repo = "Github-Demo", state = "closed")
    }

    private fun setAdapter(pulRequestList: List<PullRequestInfo>) {
        adapter = PullRequestListAdapter(pulRequestList)
        binding.rvPullRequests.adapter = adapter
    }

    private fun initObservers() {
        /*
         * show hide loader
         * */
        viewModel.loadingVisibilityLiveData.observe(this, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
        /*
        * Handle Api errors here
        * */
        viewModel.apiErrorLiveData.observe(this, Observer {
            handleApiError(it)
        })

        /*
        * Handle other Api errors here
        * */
        viewModel.otherApiErrorsLiveData.observe(this, Observer {
            handleOtherApiErrors(it)
        })

        /*
        * display list in recyclerview
        * */
        viewModel.adapterListLiveData.observe(this, Observer {
            setAdapter(it)
        })
    }
}