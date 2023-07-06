package com.example.bestkotlinprojectgithub.data

import androidx.paging.PagingData
import com.example.bestkotlinprojectgithub.UseCase
import com.example.bestkotlinprojectgithub.model.Item
import kotlinx.coroutines.flow.Flow

class ListUserRepositoryUseCase(
    private val repository: Repository
): UseCase<String, PagingData<Item>>() {

    override suspend fun execute(param: String): Flow<PagingData<Item>> {
        return repository.getKotlinProject()
    }
}