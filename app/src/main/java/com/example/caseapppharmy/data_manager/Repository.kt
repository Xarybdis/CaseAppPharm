package com.example.caseapppharmy.data_manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.caseapppharmy.data_manager.db.UsersDao
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import com.example.caseapppharmy.data_manager.modals.Pharmacy
import com.example.caseapppharmy.data_manager.modals.Result
import com.example.caseapppharmy.data_manager.modals.RssFeed
import com.example.caseapppharmy.data_manager.network.pharm.RetrofıtPharmInstance
import com.example.caseapppharmy.data_manager.network.rss.RetrofitRssInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val userDao: UsersDao) {

    suspend fun insert(user: UsersData) {
        userDao.insert(user)
    }

    val allUsers: LiveData<List<UsersData>> = userDao.getAllUsers()

    fun getUserInfo(userMail: String): LiveData<UsersData> = userDao.getUserInfo(userMail)

    suspend fun update(user: UsersData) {
        userDao.update(user)
    }

    fun getRssFeed(): LiveData<RssFeed> {
        val liveData = MutableLiveData<RssFeed>()

        RetrofitRssInstance.instance.fetchFeed().enqueue(object : Callback<RssFeed> {
            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                println("Error: Repo, news call : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }
        })
        return liveData
    }

    fun getPharmacyList(district: String, city: String): LiveData<List<Result>> {
        val liveData = MutableLiveData<List<Result>>()

        RetrofıtPharmInstance.instance.getPharmacyList(district, city).enqueue(object : Callback<Pharmacy> {
            override fun onFailure(call: Call<Pharmacy>, t: Throwable) {
                print("Here Error: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<Pharmacy>, response: Response<Pharmacy>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()?.result
                    print("Here: $liveData")
                }
            }
        })
        return liveData
    }
}