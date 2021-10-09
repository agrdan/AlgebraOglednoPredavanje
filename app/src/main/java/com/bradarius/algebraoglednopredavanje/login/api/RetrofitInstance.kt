package com.bradarius.trainingplanner.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitInstance {

    private lateinit var baseUrl: String
    private lateinit var client : OkHttpClient
    private lateinit var retrofit: Retrofit

    constructor(baseUrl: String, interceptor: Interceptor?) {
        this.baseUrl = baseUrl

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)

        if(interceptor != null) {
            clientBuilder.addInterceptor(interceptor)
        }

        client = clientBuilder.build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }


    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}