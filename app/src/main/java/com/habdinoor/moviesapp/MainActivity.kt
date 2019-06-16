package com.habdinoor.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.habdinoor.moviesapp.adapter.MoviesAdapter
import com.habdinoor.moviesapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: com.habdinoor.moviesapp.databinding.ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.main = viewModel

        setupRecyclerView()
        observeLiveData()
        viewModel.getMovies()
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager = layoutManager
        adapter = MoviesAdapter(this)
        binding.mainRecyclerView.adapter = adapter

    }

    private fun observeLiveData(){
        viewModel.movies.observe(this, Observer {
            adapter.setData(it?.results!!)
            adapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer {
            //handle error
        })
    }
}
