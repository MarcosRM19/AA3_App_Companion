package com.example.aa3_app_companion

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ClassActivity : AppCompatActivity() {

    private  lateinit var className : String
    private  lateinit var classDescription : String
    private  lateinit var classImageUrl : String
    private  lateinit var hp : String
    private  lateinit var fp : String
    private  lateinit var strength : String
    private  lateinit var dexterity : String
    private  lateinit var intelligence : String
    private  lateinit var faith : String
    private  lateinit var endurance : String
    private  lateinit var arcane : String

    private  lateinit var nameTextView : TextView
    private  lateinit var descriptionTextView : TextView
    private  lateinit var classImageView : ImageView
    private  lateinit var hpTextView: TextView
    private  lateinit var fpTextView : TextView
    private  lateinit var strengthTextView : TextView
    private  lateinit var dexterityTextView : TextView
    private  lateinit var intelligenceTextView : TextView
    private  lateinit var faithTextView : TextView
    private  lateinit var enduranceTextView : TextView
    private  lateinit var arcaneTextView : TextView
    private  lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_class)

        initializeViews()
        GetClassvalues()
        SetClassValues()

    }

    private fun initializeViews() {
        nameTextView = findViewById<TextView>(R.id.nameText)
        descriptionTextView = findViewById<TextView>(R.id.descriptionText)
        classImageView = findViewById<ImageView>(R.id.classImage)
        hpTextView = findViewById<TextView>(R.id.vigorText)
        fpTextView = findViewById<TextView>(R.id.mindText)
        strengthTextView = findViewById<TextView>(R.id.strenghtText)
        dexterityTextView = findViewById<TextView>(R.id.dexterityText)
        intelligenceTextView = findViewById<TextView>(R.id.intelligenceText)
        faithTextView = findViewById<TextView>(R.id.fiathText)
        enduranceTextView = findViewById<TextView>(R.id.enduranceText)
        arcaneTextView = findViewById<TextView>(R.id.arcaneText)
    }

    fun GetClassvalues()
    {
        className = intent.getStringExtra("CLASS_NAME") ?: "Unknown Class"
        classDescription = intent.getStringExtra("CLASS_DESCRIPTION") ?: "No description available"
        classImageUrl = intent.getStringExtra("CLASS_IMAGE_URL") ?: ""
        hp = intent.getIntExtra("CLASS_HP", 0).toString()
        fp = intent.getIntExtra("CLASS_FP", 0).toString()
        strength = intent.getIntExtra("CLASS_STRENGTH", 0).toString()
        dexterity = intent.getIntExtra("CLASS_DEXTERITY", 0).toString()
        intelligence = intent.getIntExtra("CLASS_INTELLIGENCE", 0).toString()
        faith = intent.getIntExtra("CLASS_FAITH", 0).toString()
        endurance = intent.getIntExtra("CLASS_ENDURANCE", 0).toString()
        arcane = intent.getIntExtra("CLASS_ARCANE", 0).toString()
    }

    fun SetClassValues()
    {
        nameTextView.text = className
        descriptionTextView.text = classDescription
        hpTextView.text = String.format("%s %s", getString(R.string.hp), hp)
        fpTextView.text = String.format("%s %s", getString(R.string.fb), fp)
        strengthTextView.text = String.format("%s %s", getString(R.string.strenght), strength)
        dexterityTextView.text = String.format("%s %s", getString(R.string.dexterity), dexterity)
        intelligenceTextView.text = String.format("%s %s", getString(R.string.intelligence), intelligence)
        faithTextView.text = String.format("%s %s", getString(R.string.faith), faith)
        enduranceTextView.text = String.format("%s %s", getString(R.string.endurance), endurance)
        arcaneTextView.text = String.format("%s %s", getString(R.string.arcane), arcane)

        loadImageFromUrl(classImageUrl, classImageView)
    }

    private fun loadImageFromUrl(imageUrl: String, imageView: ImageView) {
        Thread {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()

                val inputStream: InputStream = connection.inputStream
                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

                Handler(Looper.getMainLooper()).post {
                    imageView.setImageBitmap(bitmap)
                }

                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }


}