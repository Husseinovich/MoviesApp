package com.habdinoor.moviesapp.network

import com.habdinoor.moviesapp.model.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainMovieRepository {
    private val apiService = MovieAPIFactory.create()
    private val compositeDisposable = CompositeDisposable()

    fun requestMovies(onResult: (MovieResponse) -> Unit, onError: (Throwable) -> Unit){
        apiService.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: MovieAPIObserver<MovieResponse>(compositeDisposable){
                override fun onApiSuccess(data: MovieResponse) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}