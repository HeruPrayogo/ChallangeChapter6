package com.example.challangechapter6.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DiROOM {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): FavoritDatabase =
        Room.databaseBuilder(
            context,
            FavoritDatabase::class.java,
            "favorit_database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFavoriteDao(appDatabase: FavoritDatabase): FavoritDao =
        appDatabase.favoritDao()
}