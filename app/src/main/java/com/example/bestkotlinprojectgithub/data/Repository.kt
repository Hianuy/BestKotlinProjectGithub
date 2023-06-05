package com.example.bestkotlinprojectgithub.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.bestkotlinprojectgithub.model.Item
import com.example.bestkotlinprojectgithub.model.RepositoryResponse
import com.example.bestkotlinprojectgithub.presentation.PagingDataSource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getKotlinProject(): LiveData<PagingData<Item>>
}