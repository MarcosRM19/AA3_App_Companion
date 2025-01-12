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

class MagicActivity : AppCompatActivity() {

    private  lateinit var toolbar : Toolbar

    private  lateinit var nameMagic : TextView
    private  lateinit var descriptionMagic : TextView
    private  lateinit var typeMagic : TextView
    private  lateinit var intMagic : TextView
    private  lateinit var faithMagic : TextView
    private  lateinit var arcaneMagic : TextView
    private  lateinit var magicImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_magic)

        initializeViews()
        SetValues()

        toolbar = findViewById(R.id.magicbar)
        setSupportActionBar(toolbar)
    }

    private fun initializeViews() {
        nameMagic = findViewById<TextView>(R.id.magicnameText)
        descriptionMagic = findViewById<TextView>(R.id.magicdescriptionText)
        magicImageView = findViewById<ImageView>(R.id.magicImage)
        typeMagic = findViewById<TextView>(R.id.magictypeText)
        intMagic = findViewById<TextView>(R.id.intmagicText)
        faithMagic = findViewById<TextView>(R.id.faithmagicText)
        arcaneMagic = findViewById<TextView>(R.id.arcanemagicText)
    }

    private fun SetValues()
    {
        nameMagic.text = intent.getStringExtra("NAME")
        descriptionMagic.text =intent.getStringExtra("DESCRIPTION")
        typeMagic.text = intent.getStringExtra("TYPE")
        intMagic.text = getString(R.string.Int) + ": " + intent.getIntExtra("INT", 0)
        faithMagic.text = getString(R.string.Faith) + ": " + intent.getIntExtra("FAITH", 0)
        arcaneMagic.text = getString(R.string.Arcane) + ": " + intent.getIntExtra("ARCANE", 0)
        magicImageView.setImageResource(intent.getIntExtra("IMAGE", 0))
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