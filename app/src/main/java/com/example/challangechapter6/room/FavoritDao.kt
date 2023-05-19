package com.example.challangechapter6.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritDao {
    @Query("SELECT * FROM FilmFavorit")
    fun getFilmFavorite() : List<FilmFavorit>
    @Query("SELECT EXISTS(SELECT id FROM filmfavorit WHERE id = :id)")
    fun isFavoriteMovie(id : Int) : Boolean

    @Insert
    fun addFavorite(favoriteMovie:FilmFavorit)

    @Delete
    fun deleteFavorite(favoriteMovie: FilmFavorit)
}