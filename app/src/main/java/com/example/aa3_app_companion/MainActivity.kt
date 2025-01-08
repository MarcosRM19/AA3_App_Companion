package com.example.aa3_app_companion


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var  splashScreenButton : Button
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Init Firebase analytics
        analytics = FirebaseAnalytics.getInstance(this)

        // Init Firebase database
        val databaseUrl = "https://app-companion-elden-ring-default-rtdb.europe-west1.firebasedatabase.app"
        database = FirebaseDatabase.getInstance(databaseUrl).getReference("messages")


        splashScreenButton = findViewById(R.id.splashScreenButton)
        splashScreenButton.setOnClickListener {onButtonClick()}

    }

    private fun onButtonClick()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}