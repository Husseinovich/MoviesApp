package com.habdinoor.moviesapp.model

/**
 * Movie response a live of movies wrapped around a MutableList
 */
data class MovieResponse(
    val results: MutableList<Movie>
)