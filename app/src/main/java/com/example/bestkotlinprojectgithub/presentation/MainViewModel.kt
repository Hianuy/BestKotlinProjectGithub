package com.example.bestkotlinprojectgithub.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bestkotlinprojectgithub.data.Repository


class MainViewModel(repository: Repository): ViewModel() {

    val list = repository.getKotlinProject().cachedIn(viewModelScope)
}
