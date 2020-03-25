package com.example.caseapppharmy.data_manager.network.pharm

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofıtPharmInstance {
    private const val BASE_URL = "https://api.collectapi.com/"

    val instance: PharmApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(HeaderInterceptor.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PharmApi::class.java)
    }
}

class HeaderInterceptor {
    companion object {
        private const val TOKEN = "6qZ6QeNr4NdzMfChzpbhDh:0tPlz9u0W4CSgPhgh7auZX"
        var logginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val newRequest: Request = originalRequest.newBuilder()
                    .addHeader("Authorization", "apikey $TOKEN")
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(logginInterceptor)
            .build()
    }
}