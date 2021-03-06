package com.example.caseapppharmy.data_manager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UsersData::class], version = 6, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UsersDao

    companion object {
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): UsersDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext, UsersDatabase::class.java, "users_database"
                    ).fallbackToDestructiveMigration().addCallback(UserDatabaseCallback(scope)).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

    private class UserDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }

        suspend fun populateDatabase(usersDao: UsersDao) {
            usersDao.deleteAll()
            var user = UsersData("test", "test", "blabla", "blabla", "")
            usersDao.insert(user)
        }
    }
}
