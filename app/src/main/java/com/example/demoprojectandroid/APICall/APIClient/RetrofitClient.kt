package com.example.demoprojectandroid.APICall.APIClient

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val httpClient = OkHttpClient.Builder()
    private val builder =
        Retrofit.Builder()
            .baseUrl(HttpParams.APPURL )
            .addConverterFactory(GsonConverterFactory.create())

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(60, TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .baseUrl("HttpParams.appurl()")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.interceptors().add(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val credentials =
                Credentials.basic("USERNAME", "PASSWORD")
            val request = original.newBuilder()
                .build()

            chain.proceed(request)
        }

        val retrofit = builder.client(httpClient.connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()).build()
        return retrofit.create(serviceClass)
    }


}
