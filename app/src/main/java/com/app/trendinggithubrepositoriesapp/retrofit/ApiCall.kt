package com.app.trendinggithubrepositoriesapp.retrofit

import com.app.trendinggithubrepositoriesapp.model.RepositoryModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    @GET("repositories")
    suspend fun getRepositories(): Response<List<RepositoryModel>>
}