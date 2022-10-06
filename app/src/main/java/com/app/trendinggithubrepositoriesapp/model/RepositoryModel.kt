package com.app.trendinggithubrepositoriesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryModel(
   // val builtBy: List<BuiltBy> = listOf(),
    val description: String,
    val forks: Int,
    val language: String?,
    val languageColor: String?,
    @PrimaryKey(autoGenerate = false)
    val rank: Int,
    val repositoryName: String,
    val since: String,
    val starsSince: Int,
    val totalStars: Int,
    val url: String,
    val username: String
)
    data class BuiltBy(
        val avatar: String,
        val url: String,
        val username: String
    )
