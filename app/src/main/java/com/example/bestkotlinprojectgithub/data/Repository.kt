package com.example.bestkotlinprojectgithub.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.bestkotlinprojectgithub.model.Item
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getKotlinProject(): Flow<PagingData<Item>>
}