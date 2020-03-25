package com.example.caseapppharmy.data_manager.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.caseapppharmy.data_manager.db.entity.UsersData

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UsersData)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY usermail ASC")
    fun getAllUsers(): LiveData<List<UsersData>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: UsersData)

    @Query("SELECT * FROM user_table WHERE usermail = :userMail LIMIT 1 ")
    fun getUserInfo(userMail: String): LiveData<UsersData>
}