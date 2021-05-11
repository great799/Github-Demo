package com.app.githubdemo.network.model

import com.squareup.moshi.Json

data class PullRequestInfo(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "created_at") val createdAt: String,
    @field:Json(name = "closed_at") val closedAt: String,
    @field:Json(name = "user") val user: User
)

data class User(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "avatar_url") val avatarUrl: String
)