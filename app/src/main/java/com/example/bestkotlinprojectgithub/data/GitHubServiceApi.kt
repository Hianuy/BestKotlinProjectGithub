package com.example.bestkotlinprojectgithub.data

import com.example.bestkotlinprojectgithub.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubServiceApi {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    suspend fun getRepository(@Query("page") page: Int) : RepositoryResponse
}