package com.app.trendinggithubrepositoriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trendinggithubrepositoriesapp.model.RepoDetailsModel
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel
import com.app.trendinggithubrepositoriesapp.repo.RepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repository: RepositoryRepo) : ViewModel() {
    val repositoryLiveData: LiveData<List<RepositoryModel>>
        get() = repository.repositories

    var repoData: LiveData<RepoDetailsModel>? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRepositories()
        }
    }


    fun apiCall() {
        viewModelScope.launch(Dispatchers.Main) {
            val asyncValue = async(Dispatchers.IO) {
                repository.apiCall()
            }
            val value = asyncValue.await()
        }
    }

    fun getRepoDataById(id: String): LiveData<RepoDetailsModel>? {
        repoData = repository.getRepoDataById(id)
        return repoData
    }

}