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
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var  registerButton : Button
    private lateinit var  emailText : EditText
    private lateinit var  passwordText : EditText
    private lateinit var  passwordConfirmText : EditText
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Init firebase auth
        auth = FirebaseAuth.getInstance()

        //views
        registerButton = findViewById(R.id.registerButton)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.pawsswordText)
        passwordConfirmText = findViewById(R.id.confirmPawsswordText)

        registerButton.setOnClickListener {onRegisterButtonClick()}
    }

    private fun onRegisterButtonClick()
    {
        val email = emailText.text.toString().trim()
        val password = passwordText.text.toString().trim()
        val confirmPassword = passwordConfirmText.text.toString().trim()

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

        if (password != confirmPassword) {
            passwordConfirmText.error = "Passwords do not match"
            passwordConfirmText.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ClassesActivity::class.java))
                    finish()
                } else {
                    // Registration failed
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}