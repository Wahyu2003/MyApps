package com.Try.MyApps.github

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Response

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "token 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")
                    .build()
                return chain.proceed(newRequest)
            }
        })

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    val api: GithubService by lazy {
        retrofit.build().create(GithubService::class.java)
    }
}

