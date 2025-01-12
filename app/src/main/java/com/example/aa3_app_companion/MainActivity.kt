package com.example.aa3_app_companion


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var  splashScreenButton : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Init Firebase
        auth = FirebaseAuth.getInstance()

        //Check if user is already logged in
        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this, ClassesActivity::class.java))
            finish()
        }

        splashScreenButton = findViewById(R.id.splashScreenButton)
        splashScreenButton.setOnClickListener {onButtonClick()}

    }

    private fun onButtonClick()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}