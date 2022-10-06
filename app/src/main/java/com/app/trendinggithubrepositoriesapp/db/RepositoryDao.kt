package com.app.trendinggithubrepositoriesapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.trendinggithubrepositoriesapp.model.RepoDetailsModel
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insetRepository(repositoryModel: List<RepositoryModel>)

    @Query("SELECT * FROM RepositoryModel")
    fun getRepository() : List<RepositoryModel>

    @Query("SELECT * FROM RepositoryModel WHERE rank = :id")
    fun getRepoDataById(id: String) : LiveData<RepoDetailsModel>

}