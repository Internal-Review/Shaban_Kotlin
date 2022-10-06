package com.app.trendinggithubrepositoriesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.trendinggithubrepositoriesapp.repo.RepositoryRepo
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: RepositoryRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repository) as T
    }
}