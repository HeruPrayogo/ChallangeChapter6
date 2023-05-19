@file:Suppress("ClassName")

package com.example.challangechapter6.model

import com.google.gson.annotations.SerializedName

@Suppress("unused")
data class movieApi<T> (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
    )