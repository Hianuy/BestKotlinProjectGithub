package com.example.bestkotlinprojectgithub.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bestkotlinprojectgithub.data.Repository
import com.example.bestkotlinprojectgithub.model.Item
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    lateinit var list: LiveData<PagingData<Item>>
    fun initViewModelGetRepositories() = viewModelScope.launch {
        list = repository.getKotlinProject().cachedIn(viewModelScope)
    }
}

