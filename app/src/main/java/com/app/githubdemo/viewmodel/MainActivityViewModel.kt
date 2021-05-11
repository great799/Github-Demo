package com.app.githubdemo.viewmodel

import com.app.githubdemo.base.BaseViewModel
import com.app.githubdemo.network.ApiTag

class MainActivityViewModel : BaseViewModel() {
    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
    }

    override fun onApiError(errorMessage: String?, apiTag: ApiTag) {
    }

    override fun onTimeout(apiTag: ApiTag) {
    }

    override fun onNetworkError(apiTag: ApiTag) {
    }

    override fun onAuthError(apiTag: ApiTag) {
    }
}