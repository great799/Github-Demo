package com.app.githubdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.githubdemo.GithubDemoApp
import com.app.githubdemo.base.BaseViewModel
import com.app.githubdemo.network.ApiTag
import com.app.githubdemo.network.ErrorType
import com.app.githubdemo.network.model.PullRequestInfo

class PullRequestListViewModel : BaseViewModel() {

    val apiErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val otherApiErrorsLiveData: MutableLiveData<ErrorType> = MutableLiveData()
    val loadingVisibilityLiveData = MutableLiveData<Boolean>()
    val adapterListLiveData = MutableLiveData<List<PullRequestInfo>>()

    init {
        GithubDemoApp.getAppComponent().inject(this)
    }

    /*
    * Call Api to get data from server
    * */
    fun getPullRequestsFromApi(owner: String, repo: String, state: String) {
        loadingVisibilityLiveData.value = true
        callApi(serviceApi.getPullRequestsData(owner, repo, state), ApiTag.PULL_REQUEST_DATA_API)
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.PULL_REQUEST_DATA_API -> {
                loadingVisibilityLiveData.value = false
                val apiResponse = response as List<PullRequestInfo>
                adapterListLiveData.value = apiResponse
            }
        }
    }

    override fun onApiError(errorMessage: String?, apiTag: ApiTag) {
        loadingVisibilityLiveData.value = false
        apiErrorLiveData.value = errorMessage
    }

    override fun onTimeout(apiTag: ApiTag) {
        loadingVisibilityLiveData.value = false
        otherApiErrorsLiveData.value = ErrorType.TIMEOUT_ERROR
    }

    override fun onNetworkError(apiTag: ApiTag) {
        loadingVisibilityLiveData.value = false
        otherApiErrorsLiveData.value = ErrorType.NETWORK_ERROR
    }

    override fun onAuthError(apiTag: ApiTag) {
        loadingVisibilityLiveData.value = false
        otherApiErrorsLiveData.value = ErrorType.AUTH_ERROR
    }
}