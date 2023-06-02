package com.example.challangechapter6.network

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.challangechapter6.model.Result
import com.example.challangechapter6.model.movieApi
import com.google.android.play.integrity.internal.t
import org.junit.Assert.*
import org.junit.Test

class RetrofitClientTest{
    //Test untuk apakah didalame movie api field page nya berjumlah sama seperti yang diharapkan
    @Test
    fun testPage(){
        val movieApi = movieApi<t>(
            page = 1,
            results = emptyList(),
            totalPages = 10,
            totalResults = 10,
        )
        assertEquals(1, movieApi.page)
    }
    //untuk mengetest apakah jumlah total pages sama dengan jumlah yang diharapkan
    @Test
    fun testResults(){
        val movieApi = movieApi<t>(
            page = 1,
            results = emptyList(),
            totalPages = 10,
            totalResults = 10
        )
        assertEquals(10, movieApi.totalPages)
    }
    @Test
    fun testResultAdult() {
        //mengetest apakah isi boolean field adult sesuai dengan apa yang diharapkan
        val result = Result(
            adult = true,
            backdropPath = "/path/to/backdrop1.jpg",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title 1",
            overview = "Movie overview 1",
            popularity = 7.8,
            posterPath = "/path/to/poster1.jpg",
            releaseDate = "2022-01-01",
            title = "Movie Title 1",
            video = false,
            voteAverage = 8.5,
            voteCount = 1000
        )
        assertEquals(true, result.adult)
    }
    @Test
    fun testResultId(){
        val result = Result(
            adult = false,
            backdropPath = "/path/to/backdrop1.jpg",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title 1",
            overview = "Movie overview 1",
            popularity = 7.8,
            posterPath = "/path/to/poster1.jpg",
            releaseDate = "2022-01-01",
            title = "Movie Title 1",
            video = false,
            voteAverage = 8.5,
            voteCount = 1000
        )
        assertEquals(123, result.id)
    }
    @Test
    fun testResultOriginalLanguage(){
        val result = Result(
            adult = false,
            backdropPath = "/path/to/backdrop1.jpg",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title 1",
            overview = "Movie overview 1",
            popularity = 7.8,
            posterPath = "/path/to/poster1.jpg",
            releaseDate = "2022-01-01",
            title = "Movie Title 1",
            video = false,
            voteAverage = 8.5,
            voteCount = 1000
        )
        assertEquals("en", result.originalLanguage)
    }
}