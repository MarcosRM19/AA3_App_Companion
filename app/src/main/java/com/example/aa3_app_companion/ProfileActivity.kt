package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var classesButton: ImageButton
    private lateinit var itemButton: ImageButton
    private lateinit var mapButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var bossButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        InitButtons()
        ButtonsLogic()
    }

    private fun InitButtons() {
        classesButton = findViewById(R.id.classes)
        itemButton = findViewById(R.id.item)
        mapButton = findViewById(R.id.map)
        chatButton = findViewById(R.id.chat)
        bossButton = findViewById(R.id.boss)
    }

    private fun ButtonsLogic() {
        classesButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
        itemButton.setOnClickListener { ChangeActivity(ItemActivity::class.java) }
        mapButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
        bossButton.setOnClickListener { ChangeActivity(BossActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}