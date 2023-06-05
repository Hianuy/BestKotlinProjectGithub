package com.example.bestkotlinprojectgithub.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.bestkotlinprojectgithub.presentation.PagingDataSource

class RepositoryImpl(private val gitHubServiceApi: GitHubServiceApi): Repository {
    override fun getKotlinProject() = Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { PagingDataSource(gitHubServiceApi) }
        ).liveData
    }
