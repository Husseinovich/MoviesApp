package com.habdinoor.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.habdinoor.moviesapp.adapter.MoviesAdapter
import com.habdinoor.moviesapp.databinding.ActivityMainBinding
import com.habdinoor.moviesapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * The expression language allows you to write expressions that connect variables to the views in the layout.
         * The Data Binding Library automatically generates the classes required to bind the views in the layout with your data objects.
         * The library provides features such as imports, variables, and includes that you can use in your layouts.

        These features of the library coexist seamlessly with your existing layouts.
        For example, the binding variables that can be used in expressions are defined inside a data element that is a sibling of the UI layout's
        root element.
        Both elements are wrapped in a layout tag, as shown in the following example:
         */

        /**
         * // The layout for this activity is a Data Binding layout so it needs to be inflated using
        // DataBindingUtil.
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        /**
         * The Android Support Library includes the Architecture Components, which you can use to design robust, testable,
         * and maintainable apps.
         * You can use the Architecture Components with the Data Binding Library to further simplify the development of your UI.
         */

        /**
         * main here refers to the variable "name" passed in the layout for our data
         *   <data>
                <variable
                     name="main"
                    type="com.habdinoor.moviesapp.viewmodel.MainViewModel"/>
        </data>

        data describes a property that may be used within this layout.

         */
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
