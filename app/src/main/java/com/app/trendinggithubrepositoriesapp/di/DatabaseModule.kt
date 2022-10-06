package com.app.trendinggithubrepositoriesapp.di

import android.content.Context
import androidx.room.Room
import com.app.trendinggithubrepositoriesapp.db.RepositoryDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRepositoryDB(context : Context) : RepositoryDB{
        return Room.databaseBuilder(
            context,
            RepositoryDB::class.java,
            "RepositoryDB"
        ).build()
    }
}