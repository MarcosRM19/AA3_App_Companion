package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var classesButton: ImageButton
    private lateinit var itemButton: ImageButton
    private lateinit var mapButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var bossButton: ImageButton
    private lateinit var logOutButton: Button
    private lateinit var auth : FirebaseAuth
    private lateinit var nameText : TextView
    private lateinit var  database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        val dataBaseUrl = "https://app-companion-elden-ring-default-rtdb.europe-west1.firebasedatabase.app/"
        database = FirebaseDatabase.getInstance(dataBaseUrl).getReference("users")

        val dataId = database.push().key

        initViews()
        ButtonsLogic()

        showName(dataId)
    }

    private fun initViews() {
        classesButton = findViewById(R.id.classes)
        itemButton = findViewById(R.id.item)
        mapButton = findViewById(R.id.map)
        chatButton = findViewById(R.id.chat)
        bossButton = findViewById(R.id.boss)
        logOutButton = findViewById(R.id.logOutButton)
        nameText = findViewById(R.id.nameText)
    }

    private fun ButtonsLogic() {
        classesButton.setOnClickListener { changeActivity(ClassesActivity::class.java) }
        itemButton.setOnClickListener { changeActivity(ItemActivity::class.java) }
        mapButton.setOnClickListener { changeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { changeActivity(ConversationActivity::class.java) }
        bossButton.setOnClickListener { changeActivity(BossActivity::class.java) }
        logOutButton.setOnClickListener {
            auth.signOut()
            changeActivity(MainActivity::class.java)
        }

    }

    private fun changeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun showName(dataId : String?) {
        val currentUser = auth.currentUser

        if(currentUser != null) {
            val email = currentUser.email
            val databaseRef = FirebaseDatabase.getInstance().getReference("users")

        }
    }
}