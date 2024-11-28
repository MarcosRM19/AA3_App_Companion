package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var  loginButton : Button
    private lateinit var  registerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {onButtonClickLogin()}

        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {onButtonClickRegister()}
    }

    private fun onButtonClickLogin()
    {
        startActivity(Intent(this, NewsActivity::class.java))
    }

    private fun onButtonClickRegister()
    {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}