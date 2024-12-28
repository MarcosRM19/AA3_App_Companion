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

class ArmorActivity : AppCompatActivity() {

    private  lateinit var toolbar : Toolbar

    private  lateinit var nameArmor : TextView
    private  lateinit var descriptionArmor : TextView
    private  lateinit var standard : TextView
    private  lateinit var strike : TextView
    private  lateinit var slash : TextView
    private  lateinit var pierce : TextView
    private  lateinit var magic : TextView
    private  lateinit var fire : TextView
    private  lateinit var light : TextView
    private  lateinit var holy : TextView
    private  lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_armor)

        initializeViews()
        SetValues()

        toolbar = findViewById(R.id.armorbar)
        setSupportActionBar(toolbar)

    }

    private fun initializeViews() {
        nameArmor = findViewById<TextView>(R.id.armornameText)
        descriptionArmor = findViewById<TextView>(R.id.armordescriptionText)
        standard = findViewById<TextView>(R.id.standardText)
        strike = findViewById<TextView>(R.id.strikeText)
        pierce = findViewById<TextView>(R.id.pierceText)
        slash = findViewById<TextView>(R.id.slashText)
        magic = findViewById<TextView>(R.id.magicarmorText)
        fire = findViewById<TextView>(R.id.fireText)
        light = findViewById<TextView>(R.id.lightText)
        holy = findViewById<TextView>(R.id.holyText)
        image = findViewById<ImageView>(R.id.armorImage)
    }

    private fun SetValues()
    {
        val std = intent.getStringExtra("STD")?.toDoubleOrNull() ?: 0.0
        val str = intent.getStringExtra("STR")?.toDoubleOrNull() ?: 0.0
        val prc = intent.getStringExtra("PRC")?.toDoubleOrNull() ?: 0.0
        val sls = intent.getStringExtra("SLS")?.toDoubleOrNull() ?: 0.0
        val mgc = intent.getStringExtra("MGC")?.toDoubleOrNull() ?: 0.0
        val fre = intent.getStringExtra("FRE")?.toDoubleOrNull() ?: 0.0
        val lgt = intent.getStringExtra("LGT")?.toDoubleOrNull() ?: 0.0
        val hly = intent.getStringExtra("HLY")?.toDoubleOrNull() ?: 0.0

        nameArmor.text = intent.getStringExtra("NAME")
        descriptionArmor.text =intent.getStringExtra("DESCRIPTION")
        standard.text = getString(R.string.STD) + ": " + String.format("%.1f", std)
        strike.text = getString(R.string.STR) + ": " + String.format("%.1f", str)
        pierce.text = getString(R.string.PRC) + ": " + String.format("%.1f", prc)
        slash.text = getString(R.string.SLS) + ": " + String.format("%.1f", sls)
        magic.text = getString(R.string.MGC) + ": " + String.format("%.1f", mgc)
        fire.text = getString(R.string.FRE) + ": " + String.format("%.1f", fre)
        light.text = getString(R.string.LGT) + ": " + String.format("%.1f", lgt)
        holy.text = getString(R.string.HLY) + ": " + String.format("%.1f", hly)
        image.setImageResource(intent.getIntExtra("IMAGE", 0))
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