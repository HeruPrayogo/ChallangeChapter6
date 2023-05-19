@file:Suppress("unused", "unused", "unused", "unused", "unused")

package com.example.challangechapter6.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused"
)
@Entity
data class FilmFavorit(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var releasedate: String,
    var posterPath: String,
    var voteAverage: Double
) : Serializable



