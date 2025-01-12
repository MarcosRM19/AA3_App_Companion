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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var  registerButton : Button
    private lateinit var  emailText : EditText
    private lateinit var  usernameText : EditText
    private lateinit var  passwordText : EditText
    private lateinit var  passwordConfirmText : EditText
    private lateinit var  auth: FirebaseAuth
    private lateinit var  database : DatabaseReference
    private lateinit var analytics: FirebaseAnalytics
    private var startTime : Long = 0
    private var endTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        startTime = System.currentTimeMillis()

        analytics = FirebaseAnalytics.getInstance(this)

        // Init firebase auth
        auth = FirebaseAuth.getInstance()
        val dataBaseUrl = "https://app-companion-elden-ring-default-rtdb.europe-west1.firebasedatabase.app/"
        database = FirebaseDatabase.getInstance(dataBaseUrl).getReference("users")

        val dataId = database.push().key

        BindViews()

        registerButton.setOnClickListener {onRegisterButtonClick(dataId)}
    }

    override fun onResume() {
        super.onResume()
        endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.METHOD, "Time user spent while registering: $executionTime")
        }
        analytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle)
    }

    private fun BindViews()
    {
        registerButton = findViewById(R.id.registerButton)
        usernameText = findViewById(R.id.usernameText)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        passwordConfirmText = findViewById(R.id.confirmPawsswordText)
    }


    private fun onRegisterButtonClick(dataId : String?) {
        val email = emailText.text.toString().trim()
        val username = usernameText.text.toString().trim()
        val password = passwordText.text.toString().trim()
        val confirmPassword = passwordConfirmText.text.toString().trim()

        //Validate credentials
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.error = "Invalid email"
            emailText.requestFocus()
            return
        }

        if (username.isEmpty()) {
            usernameText.error = "Username is required"
            usernameText.requestFocus()
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
                    Log.d("Firebase Test", "User register successfully")
                    val user: FirebaseUser? = auth.currentUser

                    // Store user data in map
                    val userData = hashMapOf(
                        "username" to username,
                        "email" to email
                    )

                    if (dataId != null) {
                        database.child(dataId).setValue(userData)
                            .addOnSuccessListener { result ->
                                Log.d("Firebase Test", "User data Saved in db successfully")
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(Intent(this, ClassesActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener { exception ->
                                Log.d("Firebase Test", "Error: ${exception.message}")
                                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firebase Test", "User register successfully")
                Toast.makeText(this, "Error registering the user", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}