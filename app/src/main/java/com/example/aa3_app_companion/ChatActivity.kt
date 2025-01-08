package com.example.aa3_app_companion

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.ChatAdapter
import org.w3c.dom.Text

class ChatActivity : AppCompatActivity() {

    private  lateinit var toolbar : Toolbar

    private  lateinit var friendImage : ImageView
    private  lateinit var friendName : TextView

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var adapter: ChatAdapter
    private val messages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)

        toolbar = findViewById(R.id.chatcustombar)
        setSupportActionBar(toolbar)

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        adapter = ChatAdapter(messages)
        chatRecyclerView.adapter = adapter
        chatRecyclerView.layoutManager = LinearLayoutManager(this)


        ButtonLogic()
        SetFriendInformation()
    }

    private fun SetFriendInformation()
    {
        friendName = findViewById(R.id.friendName)
        friendImage = findViewById(R.id.friendImage)

        friendName.text = intent.getStringExtra("name")
        friendImage.setImageResource(intent.getIntExtra("imageResId", 0))
    }

    private  fun ButtonLogic()
    {
        sendButton.setOnClickListener {
        val message = messageInput.text.toString()
        if (message.isNotBlank()) {
            messages.add("You: $message")
            adapter.notifyItemInserted(messages.size - 1)

            chatRecyclerView.scrollToPosition(messages.size - 1)

            messageInput.text.clear()

            simulateResponse()
        }
    }

    }

    private fun simulateResponse() {
        chatRecyclerView.postDelayed({
            messages.add("Other: OK")
            adapter.notifyItemInserted(messages.size - 1)
            chatRecyclerView.scrollToPosition(messages.size - 1)
        }, 1000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_class_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.arrow -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}