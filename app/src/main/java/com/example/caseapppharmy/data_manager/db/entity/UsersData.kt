package com.example.caseapppharmy.data_manager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UsersData(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val userName: String,
    val password: String,
    val photo: Int?
)