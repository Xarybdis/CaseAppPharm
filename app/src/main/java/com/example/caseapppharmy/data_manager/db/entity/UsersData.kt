package com.example.caseapppharmy.data_manager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UsersData(
    @PrimaryKey
    @ColumnInfo(name = "usermail")
    val userMail: String,
    @ColumnInfo(name = "password")
    val password: String?,
    @ColumnInfo(name = "firstname")
    val firstName: String?,
    @ColumnInfo(name = "lastname")
    val lastName: String?,
    val photo: String?
)