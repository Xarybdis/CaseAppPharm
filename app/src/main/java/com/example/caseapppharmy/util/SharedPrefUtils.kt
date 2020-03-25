package com.example.caseapppharmy.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPrefUtils {
    companion object {

        private const val USER_MAIL = "user_mail"
        private const val USER_PASS = "user_pass"

        fun saveUserMail(context: Context, userMail: String) {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
            sharedPref.putString(USER_MAIL, userMail).apply()
        }

        fun saveUserPassWord(context: Context, userPass: String) {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
            sharedPref.putString(USER_PASS, userPass).apply()
        }

        fun getUserMail(context: Context): String {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            val string = sharedPref.getString(USER_MAIL, "")
            return string.toString()
        }

        fun getUserPass(context: Context): String {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            val string = sharedPref.getString(USER_PASS, "")
            return string.toString()
        }

        fun clearUserCredentials(context: Context) {
            val shared = PreferenceManager.getDefaultSharedPreferences(context)
            val editor :SharedPreferences.Editor = shared.edit()
            editor.remove(USER_MAIL)
            editor.remove(USER_PASS)
            editor.commit()
        }
    }
}