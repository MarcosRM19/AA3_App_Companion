package com.example.aa3_app_companion

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class TalismanActivity : AppCompatActivity() {

    private  lateinit var toolbar : Toolbar

    private  lateinit var nameTalisman : TextView
    private  lateinit var descriptionTalisman : TextView
    private  lateinit var effectTalisman : TextView
    private  lateinit var classImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_talisman)

        initializeViews()
        SetValues()

        toolbar = findViewById(R.id.talismanBar)
        setSupportActionBar(toolbar)
    }

    private fun initializeViews() {
        nameTalisman = findViewById<TextView>(R.id.nameTextTalisman)
        descriptionTalisman = findViewById<TextView>(R.id.descriptionTalisman)
        classImageView = findViewById<ImageView>(R.id.talismanImage)
        effectTalisman = findViewById<TextView>(R.id.effectTalisman)
    }

    private fun SetValues()
    {
        nameTalisman.text = intent.getStringExtra("NAME")
        descriptionTalisman.text =intent.getStringExtra("DESCRIPTION")
        effectTalisman.text = intent.getStringExtra("EFFECT")
        classImageView.setImageResource(intent.getIntExtra("IMAGE", 0))
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