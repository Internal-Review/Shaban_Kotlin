package com.app.trendinggithubrepositoriesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel

@Database(entities = [RepositoryModel::class], version = 1)
abstract class RepositoryDB : RoomDatabase() {

    abstract fun getRepositoryDao() : RepositoryDao

}