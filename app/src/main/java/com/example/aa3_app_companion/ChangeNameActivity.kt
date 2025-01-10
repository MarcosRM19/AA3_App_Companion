package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChangeNameActivity : AppCompatActivity() {

    private lateinit var changeNameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_name)

        initViews();
        buttonsLogic()
    }

    private fun initViews() {
        changeNameButton = findViewById(R.id.confirmButton)
    }

    private fun buttonsLogic() {
        changeNameButton.setOnClickListener { changeActivity(ProfileActivity::class.java) }
    }

    private fun changeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}