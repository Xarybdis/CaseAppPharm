package com.example.caseapppharmy.data_manager.network.rss

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RetrofitRssInstance {
    private const val RSS_FEED_URL = "https://www.donanimhaber.com/"

    val instance: ApiXmlRead by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(RSS_FEED_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        retrofit.create(ApiXmlRead::class.java)
    }
}