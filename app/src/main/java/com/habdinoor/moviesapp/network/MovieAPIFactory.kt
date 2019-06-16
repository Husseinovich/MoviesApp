package com.habdinoor.moviesapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieAPIFactory {
    /**
     * To send out network requests to an API, we need to use the Retrofit builder class
     * and specify the base URL for the service.
     */
    val BASE_URL = "https://api.themoviedb.org/3/movie/"

    fun create(): MovieInterface {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(logging)
        val client = clientBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
                // we need to specify a factory for deserializing the response using the Gson library
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(MovieInterface::class.java)

    }
}