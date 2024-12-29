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

class WeaponActivity : AppCompatActivity() {

    private  lateinit var toolbar : Toolbar

    private  lateinit var nameWeapon : TextView
    private  lateinit var descriptionWeapon : TextView
    private  lateinit var physical : TextView
    private  lateinit var magic : TextView
    private  lateinit var fire : TextView
    private  lateinit var light : TextView
    private  lateinit var holy : TextView
    private  lateinit var critical : TextView
    private  lateinit var image : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weapon)

        initializeViews()
        SetValues()

        toolbar = findViewById(R.id.weaponbar)
        setSupportActionBar(toolbar)
    }

    private fun initializeViews() {
        nameWeapon = findViewById<TextView>(R.id.weaponnameText)
        descriptionWeapon = findViewById<TextView>(R.id.weapondescriptionText)
        physical = findViewById<TextView>(R.id.physicalText)
        critical = findViewById<TextView>(R.id.criticalText)
        magic = findViewById<TextView>(R.id.magicweaponText)
        fire = findViewById<TextView>(R.id.fireweaponText)
        light = findViewById<TextView>(R.id.lightingText)
        holy = findViewById<TextView>(R.id.holyweaponText)
        image = findViewById<ImageView>(R.id.weaponImage)
    }

    private fun SetValues()
    {
        val phs = intent.getStringExtra("PHS")?.toDoubleOrNull() ?: 0.0
        val crt = intent.getStringExtra("CRT")?.toDoubleOrNull() ?: 0.0
        val mgc = intent.getStringExtra("MGC")?.toDoubleOrNull() ?: 0.0
        val fre = intent.getStringExtra("FRE")?.toDoubleOrNull() ?: 0.0
        val lgt = intent.getStringExtra("LGT")?.toDoubleOrNull() ?: 0.0
        val hly = intent.getStringExtra("HLY")?.toDoubleOrNull() ?: 0.0

        nameWeapon.text = intent.getStringExtra("NAME")
        descriptionWeapon.text =intent.getStringExtra("DESCRIPTION")
        physical.text = getString(R.string.PHS) + ": " + String.format("%.1f", phs)
        critical.text = getString(R.string.CRT) + ": " + String.format("%.1f", crt)
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