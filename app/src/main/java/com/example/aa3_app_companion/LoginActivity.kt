package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var  loginButton : Button
    private lateinit var  registerButton : Button
    private lateinit var  emailText : EditText
    private lateinit var  passwordText : EditText
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        emailText = findViewById(R.id.usernameText)
        passwordText = findViewById(R.id.pawsswordText)

        //Button listeners
        loginButton.setOnClickListener {onButtonClickLogin()}
        registerButton.setOnClickListener {onButtonClickRegister()}
    }

    private fun onButtonClickLogin()
    {

        val email = emailText.text.toString().trim()
        val password = passwordText.text.toString().trim()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        //Validate credentials
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.error = "Invalid email"
            emailText.requestFocus()
            return
        }

        if (password.length < 6) {
            passwordText.error = "Password must be at least 6 characters"
            passwordText.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    Log.d("FirebaseAuth", "Login successful for user: ${auth.currentUser?.email}")
                    // Navigate to the next activity
                    startActivity(Intent(this, ClassesActivity::class.java))
                    finish()
                } else {
                    // Login failed
                    val errorMessage = task.exception?.localizedMessage ?: "Login failed"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("FirebaseAuth", "Login failed: $errorMessage")
                }
            }
    }

    private fun onButtonClickRegister()
    {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}