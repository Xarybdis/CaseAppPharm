package com.example.caseapppharmy.data_manager.network.rss

import com.example.caseapppharmy.data_manager.modals.RssFeed
import retrofit2.Call
import retrofit2.http.GET

interface ApiXmlRead {

    @GET("rss/tum/")
    fun fetchFeed(): Call<RssFeed>
}