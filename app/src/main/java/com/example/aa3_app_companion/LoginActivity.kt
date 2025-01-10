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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var  loginButton : Button
    private lateinit var  registerButton : Button
    private lateinit var  emailText : EditText
    private lateinit var  passwordText : EditText
    private lateinit var googleButton : com.google.android.gms.common.SignInButton
    private lateinit var  auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webClientId))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        emailText = findViewById(R.id.usernameText)
        passwordText = findViewById(R.id.pawsswordText)
        googleButton = findViewById(R.id.google_sign_in_button)

        //Button listeners
        loginButton.setOnClickListener {onButtonClickLogin()}
        registerButton.setOnClickListener {onButtonClickRegister()}
        googleButton.setOnClickListener {onButtonClickGoogle()}

    }

    private fun onButtonClickGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign-In was successful, authenticate with Firebase
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign-In failed, update UI appropriately
                Log.w("GoogleSignIn", "Google sign in failed", e)
                Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        Log.d("GoogleSignIn", "firebaseAuthWithGoogle:" + acct?.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                    startActivity(Intent(this, ClassesActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSignIn", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Redirect to another activity or update UI
            Log.d("GoogleSignIn", "User signed in: ${user.displayName}")
            Toast.makeText(this, "Welcome, ${user.displayName}", Toast.LENGTH_SHORT).show()
            // Optionally save the user to Firebase Database
            val database = FirebaseDatabase.getInstance().getReference("users")
            database.child(user.uid).setValue(user.displayName)
        } else {
            // User not signed in, show login screen or handle accordingly
            Log.d("GoogleSignIn", "User not signed in")
        }
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