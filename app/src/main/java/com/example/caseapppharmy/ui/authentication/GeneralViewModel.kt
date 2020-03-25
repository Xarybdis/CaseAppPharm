package com.example.caseapppharmy.ui.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.caseapppharmy.data_manager.Repository
import com.example.caseapppharmy.data_manager.db.UsersDatabase
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import com.example.caseapppharmy.data_manager.modals.Result
import com.example.caseapppharmy.data_manager.modals.RssFeed
import kotlinx.coroutines.launch

class GeneralViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    val allUsers: LiveData<List<UsersData>>
    private val news: LiveData<RssFeed>
    private lateinit var getUserInfo: LiveData<UsersData>

    init {
        val usersDao = UsersDatabase.getDatabase(application, viewModelScope).userDao()
        repository = Repository(usersDao)
        allUsers = repository.allUsers
        news = repository.getRssFeed()
    }

    fun insert(user: UsersData) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: UsersData) = viewModelScope.launch {
        repository.update(user)
    }

    fun fetchPharmacyList(district: String, city: String): LiveData<List<Result>> {
        return repository.getPharmacyList(district, city)
    }
}