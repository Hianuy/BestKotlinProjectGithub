package com.example.bestkotlinprojectgithub.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class RepositoryImpl(private val gitHubServiceApi: GitHubServiceApi) : Repository {
    override suspend fun getKotlinProject() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
        pagingSourceFactory = { PagingDataSource(gitHubServiceApi) }
    ).flow

    companion object {
        const val PAGE_SIZE = 20
        const val MAX_SIZE = 100
    }
}


