package com.habdinoor.moviesapp.network

import com.habdinoor.moviesapp.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieInterface {
    @GET("upcoming?api_key=78adf61cd991fec888c055105c148a44")
    fun getMovies(): Observable<MovieResponse>
}