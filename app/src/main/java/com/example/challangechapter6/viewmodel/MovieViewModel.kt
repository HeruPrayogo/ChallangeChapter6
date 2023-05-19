package com.example.challangechapter6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangechapter6.model.Result
import com.example.challangechapter6.model.movieApi
import com.example.challangechapter6.network.RestfulApi
import com.example.challangechapter6.room.FavoritDao
import com.example.challangechapter6.room.FilmFavorit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Suppress("PrivatePropertyName", "unused")
@HiltViewModel
class MovieViewModel @Inject constructor(private var api: RestfulApi, private val db: FavoritDao):ViewModel(){
    //untuk detail
    var liveDataMovie: MutableLiveData<List<Result>> = MutableLiveData()
    var liveDetail: MutableLiveData<Result> = MutableLiveData()
    val movie: LiveData<Result> get() = liveDetail
    //untuk favorit
    private var liveFav : MutableLiveData<FilmFavorit> = MutableLiveData()
    val favMovie: LiveData<FilmFavorit> get() = liveFav
    private var liveFavDel: MutableLiveData<FilmFavorit> = MutableLiveData()
    val deleteFavMovie: LiveData<FilmFavorit> get() = liveFavDel
    private val _IsFav: MutableLiveData<Boolean> = MutableLiveData()
    val isFav: LiveData<Boolean> get() = _IsFav
    fun getMovie() {
        api.getPopularMovies(
            apiKey = "e73ba4baa44323fa06e5497760f26ab5",
            page = 1
        ).enqueue(object : Callback<movieApi<Result>> {
            override fun onResponse(call: Call<movieApi<Result>>, response: Response<movieApi<Result>>) {
                if (response.isSuccessful){
                    val movieresponse = response.body()
                    liveDataMovie.postValue(movieresponse?.results)

                }else{
                    liveDataMovie.value = emptyList()
                }
            }

            override fun onFailure(call: Call<movieApi<Result>>, t: Throwable) {
                liveDataMovie.value = emptyList()
            }

        })


    }
    fun getMovieDetail(movieId:Int) {
        api.getMovieDetails(movieId, "e73ba4baa44323fa06e5497760f26ab5")
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (response.isSuccessful) {
                        val movie = response.body()
                        liveDetail.value = movie!!
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    liveDataMovie.value = emptyList()
                }

            })
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun isFavoriteMovie(id: Int) {
        GlobalScope.launch {
            _IsFav.postValue(db.isFavoriteMovie(id))
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun addFavMovie(favMovie: FilmFavorit) {
        GlobalScope.launch {
            db.addFavorite(favMovie)
            liveFav.postValue(favMovie)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteFavMovie(favMovie: FilmFavorit) {
        GlobalScope.launch {
            db.deleteFavorite(favMovie)
            liveFavDel.postValue(favMovie)
        }
    }



}