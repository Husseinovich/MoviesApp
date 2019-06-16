package com.habdinoor.moviesapp.network

import com.habdinoor.moviesapp.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieInterface {
    /**
     * With Retrofit endpoints are defined inside of an interface using special retrofit annotations to encode
     * details about the parameters and request method.
     * In addition, the return value is always a parameterized Call<T>.
     * However, in this case we can specify return value as simply Call<ResponseBody>
     */
    @GET("upcoming?api_key=78adf61cd991fec888c055105c148a44")
    fun getMovies(): Observable<MovieResponse>
}