package com.example.caseapppharmy.ui.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.caseapppharmy.data_manager.Repository
import com.example.caseapppharmy.data_manager.db.UsersDatabase
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    val allUsers: LiveData<List<UsersData>>

    init {
        val usersDao = UsersDatabase.getDatabase(application, viewModelScope).userDao()
        repository = Repository(usersDao)
        allUsers = repository.allUsers
    }

    fun insert(user: UsersData) = viewModelScope.launch {
        repository.insert(user)
    }

}