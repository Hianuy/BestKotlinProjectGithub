package com.example.bestkotlinprojectgithub.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bestkotlinprojectgithub.model.Item
import java.lang.Exception

class PagingDataSource(private val githubApi: GitHubServiceApi): PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        return try {
            val position = params.key ?: 1
            val response = githubApi.getRepository(position)

            return LoadResult.Page(
                data = response.items,
                prevKey = if (position == INIT_PAGE) null else position - 1,
                nextKey = if (position == LAST_PAGE) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    private companion object{
        const val INIT_PAGE = 1
        const val LAST_PAGE = 1151864
    }
}