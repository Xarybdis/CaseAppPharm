package com.example.caseapppharmy.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caseapppharmy.MainActivity
import com.example.caseapppharmy.R
import com.example.caseapppharmy.util.SharedPrefUtils
import com.example.caseapppharmy.util.Utilities
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var viewModel: GeneralViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)

        checkLogCredentials()

        toRegister.setOnClickListener {
            toRegister()
        }

        loginUser.setOnClickListener {
            login()
        }
    }

    private fun login() {
        Utilities.validateFields(userMail, userPassword)

        val userMail = userMail.text.toString()
        val userPassword = userPassword.text.toString()
        viewModel.allUsers.observe(this, Observer {
            for (i in it) {
                if (i.userMail == userMail && i.password == userPassword) {
                    saveLogCredits(userMail, userPassword)
                    startActivity(Intent(this@Login, MainActivity::class.java))
                    finish()
                } else
                    Toast.makeText(this, "Email veya şifrenizi kontrol edin.", Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }

    private fun toRegister() {
        startActivity(Intent(this@Login, SignUp::class.java))
        finish()
    }

    fun saveLogCredits(mail: String, pass: String) {
        SharedPrefUtils.saveUserMail(this, mail)
        SharedPrefUtils.saveUserPassWord(this, pass)
    }

    private fun checkLogCredentials() {
        val userMail = SharedPrefUtils.getUserMail(this).isNotEmpty()
        val userPass = SharedPrefUtils.getUserPass(this).isNotEmpty()
        if (userMail && userPass) {
            startActivity(Intent(this@Login, MainActivity::class.java))
        }
    }
}