package com.example.caseapppharmy.data_manager

import androidx.lifecycle.LiveData
import com.example.caseapppharmy.data_manager.db.UsersDao
import com.example.caseapppharmy.data_manager.db.entity.UsersData

class Repository(private val userDao: UsersDao) {

    suspend fun insert(user: UsersData) {
        userDao.insert(user)
    }

    val allUsers: LiveData<List<UsersData>> = userDao.getAllUsers()
}