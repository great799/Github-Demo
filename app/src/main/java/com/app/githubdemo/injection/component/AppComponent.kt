package com.app.githubdemo.injection.component

import com.app.githubdemo.injection.module.AppModule
import com.app.githubdemo.injection.module.NetworkModule
import com.app.githubdemo.ui.adapter.PullRequestListAdapter
import com.app.githubdemo.viewmodel.PullRequestListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(pullRequestListViewModel: PullRequestListViewModel)
    fun inject(pullRequestListAdapter: PullRequestListAdapter)
}