package com.example.caseapppharmy.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.caseapppharmy.R
import com.example.caseapppharmy.data_manager.db.entity.UsersData
import com.example.caseapppharmy.util.Utilities
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {
    private lateinit var viewModel: GeneralViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)

        registerUser.setOnClickListener {
            saveUser()
        }

        toLogin.setOnClickListener {
            toLogin()
        }
    }

    private fun saveUser() {
        Utilities.validateFields(userMail, userPassword)
        var user = UsersData(
            userMail.text.toString(), userPassword.text.toString(), "", "", ""
        )
        viewModel.insert(user)

        Toast.makeText(this, "Kullanıcı başarıyla kaydedilmiştir.", Toast.LENGTH_LONG).show()
        GlobalScope.launch {
            delay(1000)
            startActivity(Intent(this@SignUp, Login::class.java))
            finish()
        }
    }

    private fun toLogin() {
        startActivity(Intent(this@SignUp, Login::class.java))
        finish()
    }
}