package com.example.challangechapter6.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FilmFavorit::class], version = 1, exportSchema = false)
abstract class FavoritDatabase : RoomDatabase(){
    abstract fun favoritDao() : FavoritDao
}