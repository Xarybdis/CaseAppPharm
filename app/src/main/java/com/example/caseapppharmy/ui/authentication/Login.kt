package com.example.caseapppharmy.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caseapppharmy.MainActivity
import com.example.caseapppharmy.R
import com.example.caseapppharmy.base.BaseActivity
import com.example.caseapppharmy.util.Utilities
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity() {
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        toRegister.setOnClickListener {
            toRegister()
        }

        loginUser.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (!Utilities.validateFields(userMail, userPassword)) {
            return
        }

        val userMail = userMail.text.toString()
        val userPassword = userPassword.text.toString()
        viewModel.allUsers.observe(this, Observer {
            for (i in it) {
                if (i.userName.equals(userMail) && i.password.equals(userPassword)) {
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
}