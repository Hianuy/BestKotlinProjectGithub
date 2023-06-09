package com.example.bestkotlinprojectgithub.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.bestkotlinprojectgithub.data.ListUserRepositoryUseCase
import com.example.bestkotlinprojectgithub.model.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val userCase: ListUserRepositoryUseCase) : ViewModel() {

    private val _list = MutableLiveData<State>()
     val list: LiveData<State> = _list

    fun initViewModelGetRepositories() = viewModelScope.launch {
        userCase.execute("")
            .onStart {
                _list.postValue(State.Loading)
            }
            .catch {
                _list.postValue(State.Error(it))
            }
            .collect{
                _list.postValue(State.Success(it))
            }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: PagingData<Item>) : State()
        data class Error(val error: Throwable) : State()
    }

}

