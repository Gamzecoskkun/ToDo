package com.gamzecoskun.todo.di

import android.content.Context
import androidx.room.Room
import com.gamzecoskun.todo.data.AppDatabase
import com.gamzecoskun.todo.utilities.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModel {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(
        context,AppDatabase::class.java,DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideToDODao(database: AppDatabase)=database.toDoDao()
}