package com.example.aa3_app_companion

import FriendAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Friend

class ConversationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mapButton: ImageButton
    private lateinit var itemButton: ImageButton
    private lateinit var bossButton: ImageButton
    private lateinit var profileButton: ImageButton
    private lateinit var classButton: ImageButton
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_conversation)

        recyclerView = findViewById(R.id.conversation_recycler_view)
        val friend = listOf(
            Friend(R.drawable.radagon, "MiKolegon06"),
            Friend(R.drawable.marika, "XxTerreneitorxX"),
            Friend(R.drawable.melenia, "Anuel AAA"),
            Friend(R.drawable.melina, "CalamardoUWU"),
            Friend(R.drawable.alexandericon, "MarcianoRajoy")
        )

        val adapter = FriendAdapter(friend)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        InitButtons()
        ButtonsLogic()
    }

    private fun InitButtons() {
        mapButton = findViewById(R.id.map)
        itemButton = findViewById(R.id.item)
        bossButton = findViewById(R.id.boss)
        profileButton = findViewById(R.id.profile)
        classButton = findViewById(R.id.classes)
    }

    private fun ButtonsLogic() {
        mapButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        itemButton.setOnClickListener { ChangeActivity(ItemActivity::class.java) }
        bossButton.setOnClickListener { ChangeActivity(BossActivity::class.java) }
        profileButton.setOnClickListener { ChangeActivity(ProfileActivity::class.java) }
        classButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}