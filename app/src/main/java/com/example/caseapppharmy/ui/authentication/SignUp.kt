package com.example.caseapppharmy.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.caseapppharmy.R
import com.example.caseapppharmy.base.BaseActivity
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import com.example.caseapppharmy.util.Utilities
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUp : BaseActivity() {
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        registerUser.setOnClickListener {
            saveUser()
        }

        toLogin.setOnClickListener {
            toLogin()
        }
    }

    private fun saveUser() {
        if (Utilities.validateFields(userMail, userPassword)) {
            var user = UsersData(
                userMail.text.toString(), userPassword.text.toString(), R.drawable.ic_user_holder
            )
            viewModel.insert(user)

            Toast.makeText(this, "Kullanıcı başarıyla kaydedilmiştir.", Toast.LENGTH_LONG).show()
            GlobalScope.launch {
                delay(1000)
                startActivity(Intent(this@SignUp, Login::class.java))
                finish()
            }
        } else
            return
    }

    private fun toLogin() {
        startActivity(Intent(this@SignUp, Login::class.java))
        finish()
    }
}