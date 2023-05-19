@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "PrivatePropertyName", "JoinDeclarationAndAssignment",
    "JoinDeclarationAndAssignment"
)

package com.example.challangechapter6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangechapter6.room.FavoritDao
import com.example.challangechapter6.room.FilmFavorit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "PropertyName", "JoinDeclarationAndAssignment"
)
@HiltViewModel
class FavoritViewModel @Inject constructor(private val db: FavoritDao): ViewModel(){
    private val fav: MutableLiveData<FilmFavorit> = MutableLiveData()
    val favmovie: LiveData<FilmFavorit> get() = fav
    private val ListMovie: MutableLiveData<List<FilmFavorit>>
    val listMovie: LiveData<List<FilmFavorit>> get() = ListMovie

    init {
        ListMovie = MutableLiveData()
    }
    fun getAllFavoriteMovie() {
        GlobalScope.launch {
            ListMovie.postValue(db.getFilmFavorite())
        }
    }
    fun deleteFavMovie(favMovie: FilmFavorit) {
        GlobalScope.launch {
            db.deleteFavorite(favMovie)
            fav.postValue(favMovie)
        }
    }
}