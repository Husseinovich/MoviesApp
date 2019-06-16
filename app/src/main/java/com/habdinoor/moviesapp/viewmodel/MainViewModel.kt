package com.habdinoor.moviesapp.viewmodel;

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.habdinoor.moviesapp.model.MovieResponse
import com.habdinoor.moviesapp.network.MainMovieRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainMovieRepository()

    var movies : MutableLiveData<MovieResponse> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getMovies(){
        repository.requestMovies({
            movies.postValue(it)
        },{
            error.postValue(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}