package com.app.githubdemo.network

import com.app.githubdemo.network.model.PullRequestInfo
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ServiceApi {
    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequestsData(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String
    ): Observable<Response<List<PullRequestInfo>>>
}