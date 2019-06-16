package com.habdinoor.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.habdinoor.moviesapp.R
import com.habdinoor.moviesapp.databinding.MovieItemBinding
import com.habdinoor.moviesapp.model.Movie
import com.habdinoor.moviesapp.viewmodel.ItemMovieViewModel

class MoviesAdapter(val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {



    protected var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_item, parent, false);
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[holder.adapterPosition])
    }


    fun setData(movie: MutableList<Movie>){
        this.movies = movie
    }

    class ViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: Movie){
            val viewModel = ItemMovieViewModel(model)
            /**
             * itemMovie is the name given to the data variable in the movie_item.xml file
             * We then pass the view mode instead of manually setting the view using findviewbyid.
             */
            binding.itemMovie = viewModel

        }
    }
}