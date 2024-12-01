package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MapActivity : AppCompatActivity() {

    private lateinit var classesButton : ImageButton
    private lateinit var itemButton : ImageButton
    private lateinit var bossButton : ImageButton
    private lateinit var chatButton : ImageButton
    private lateinit var profileButton : ImageButton

    private  lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        InitButtons()
        ButtonsLogic()
    }

    private  fun InitButtons()
    {
        classesButton = findViewById(R.id.classes);
        itemButton = findViewById(R.id.item);
        bossButton = findViewById(R.id.boss);
        chatButton = findViewById(R.id.chat);
        profileButton = findViewById(R.id.profile);
    }

    private fun ButtonsLogic()
    {
        classesButton.setOnClickListener{(ChangeActivity(ClassesActivity::class.java))}
        itemButton.setOnClickListener{(ChangeActivity(ClassesActivity::class.java))}
        bossButton.setOnClickListener{(ChangeActivity(BossActivity::class.java))}
        chatButton.setOnClickListener{(ChangeActivity(ClassesActivity::class.java))}
        profileButton.setOnClickListener{(ChangeActivity(ClassesActivity::class.java))}
    }

    private fun ChangeActivity(activityClass: Class<*>)
    {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}