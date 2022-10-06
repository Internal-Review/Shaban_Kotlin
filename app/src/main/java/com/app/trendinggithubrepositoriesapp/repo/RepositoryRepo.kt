package com.app.trendinggithubrepositoriesapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.trendinggithubrepositoriesapp.db.RepositoryDB
import com.app.trendinggithubrepositoriesapp.model.RepoDetailsModel
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel
import com.app.trendinggithubrepositoriesapp.retrofit.ApiCall
import javax.inject.Inject

class RepositoryRepo @Inject constructor(private val apiCall: ApiCall, private val repositoryDB: RepositoryDB) {
    private val _repositories = MutableLiveData<List<RepositoryModel>>()
    val repositories: LiveData<List<RepositoryModel>>
    get() = _repositories

    private var repoData: LiveData<RepoDetailsModel>? = null

    // Api Call
    suspend fun apiCall(){
        val result = apiCall.getRepositories()
        if (result.isSuccessful && result.body()!= null){
           repositoryDB.getRepositoryDao().insetRepository(result.body()!!)
        }
    }
    // get all repository from local database
    fun getRepositories(){
        _repositories.postValue(repositoryDB.getRepositoryDao().getRepository())
    }

    // get repository by id
    fun getRepoDataById( id: String) : LiveData<RepoDetailsModel>? {
        repoData = repositoryDB.getRepositoryDao().getRepoDataById(id)
        return repoData
    }
}