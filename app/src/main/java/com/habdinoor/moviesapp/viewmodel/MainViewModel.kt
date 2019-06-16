package com.habdinoor.moviesapp.viewmodel;

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.habdinoor.moviesapp.model.MovieResponse
import com.habdinoor.moviesapp.network.MainMovieRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    /**
     * A ViewModel object provides the data for a specific UI component, such as a fragment or activity,
     * and contains data-handling business logic to communicate with the model.
     * For example, the ViewModel can call other components to load the data, and it can forward user requests to modify the data.
     * The ViewModel doesn't know about UI components, so it isn't affected by configuration changes, such as recreating an activity when rotating the device.
     */

    private val repository = MainMovieRepository()

    /**
     * LiveData is an observable data holder. Other components in your app can monitor changes to objects using this
     * holder without creating explicit and rigid dependency paths between them.
     * The LiveData component also respects the lifecycle state of your app's components—such as activities, fragments,
     * and services—and includes cleanup logic to prevent object leaking and excessive memory consumption.
     */

    /**
     * MutableLiveData is a subclass of LiveData which is used for some of it’s properties (setValue/postValue) and
     * using these properties we can easily notify the ui when onChange() is called. Only using LiveData object we can’t do this.
     * So, we have to convert the target object to MutableLiveData/MediatorLiveData for notifying on each time of changing data.
     */
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