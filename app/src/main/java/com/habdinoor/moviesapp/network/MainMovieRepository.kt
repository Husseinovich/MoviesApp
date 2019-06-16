package com.habdinoor.moviesapp.network

import com.habdinoor.moviesapp.model.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainMovieRepository {
    private val apiService = MovieAPIFactory.create()
    private val compositeDisposable = CompositeDisposable()

    /**
     * Instead, our ViewModel delegates the data-fetching process to a new module, a repository.

    Repository modules handle data operations. They provide a clean API so that the rest of the app can retrieve this data easily.
    They know where to get the data from and what API calls to make when data is updated. You can consider
    repositories to be mediators between different data sources, such as persistent models, web services, and caches.


     */

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