package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Classes
import models.ClassesAdapter
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class ClassesActivity : AppCompatActivity(), ClassesAdapter.OnButtonClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mapButton : ImageButton
    private lateinit var itemButton : ImageButton
    private lateinit var bossButton : ImageButton
    private lateinit var chatButton : ImageButton
    private lateinit var profileButton : ImageButton
    private  lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        InitButtons()

        recyclerView = findViewById(R.id.classes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        fetchClassesData()
        ButtonsLogic()
    }

    override fun onButtonClick(classes: Classes) {
        val intent = Intent(this, ClassActivity::class.java)
        intent.putExtra("CLASS_NAME", classes.name)
        intent.putExtra("CLASS_DESCRIPTION", classes.description)
        intent.putExtra("CLASS_IMAGE_URL", classes.image)

        intent.putExtra("CLASS_HP", classes.hp)
        intent.putExtra("CLASS_FP", classes.fp)
        intent.putExtra("CLASS_STRENGTH", classes.strength)
        intent.putExtra("CLASS_DEXTERITY", classes.dexterity)
        intent.putExtra("CLASS_INTELLIGENCE", classes.intelligence)
        intent.putExtra("CLASS_FAITH", classes.faith)
        intent.putExtra("CLASS_ENDURANCE", classes.endurance)
        intent.putExtra("CLASS_ARCANE", classes.endurance)

        startActivity(intent)
    }

    private fun fetchClassesData() {
        val apiUrl = "https://eldenring.fanapis.com/api/classes?limit=20"

        thread {
            try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().use { it.readText() }

                    val jsonObject = JSONObject(response)
                    val classesArray = jsonObject.getJSONArray("data")

                    val classesList = mutableListOf<Classes>()
                    for (i in 0 until classesArray.length()) {
                        val classObject = classesArray.getJSONObject(i)
                        val name = classObject.getString("name")
                        val description = classObject.getString("description")
                        val image = classObject.getString("image")

                        val stats = classObject.getJSONObject("stats")
                        val vigor = stats.optInt("vigor", 0)
                        val mind = stats.optInt("mind", 0)
                        val endurance = stats.optInt("endurance", 0)
                        val strength = stats.optInt("strength", 0)
                        val dexterity = stats.optInt("dexterity", 0)
                        val intelligence = stats.optInt("intelligence", 0)
                        val faith = stats.optInt("faith", 0)
                        val arcane = stats.optInt("arcane", 0)

                        val classe = Classes(name, description, image, vigor, mind, strength, dexterity, intelligence, faith, endurance)
                        classesList.add(classe)
                    }

                    runOnUiThread {
                        recyclerView.adapter = ClassesAdapter(classesList, this)
                    }

                } else {
                    runOnUiThread {
                        Toast.makeText(this, "Error al conectar: ${connection.responseCode}", Toast.LENGTH_SHORT).show()
                    }
                }
                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private  fun InitButtons()
    {
        mapButton = findViewById(R.id.map);
        itemButton = findViewById(R.id.item);
        bossButton = findViewById(R.id.boss);
        chatButton = findViewById(R.id.chat);
        profileButton = findViewById(R.id.profile);
    }

    private fun ButtonsLogic()
    {
        mapButton.setOnClickListener{(ChangeActivity(MapActivity::class.java))}
        itemButton.setOnClickListener{(ChangeActivity(MapActivity::class.java))}
        bossButton.setOnClickListener{(ChangeActivity(MapActivity::class.java))}
        chatButton.setOnClickListener{(ChangeActivity(MapActivity::class.java))}
        profileButton.setOnClickListener{(ChangeActivity(MapActivity::class.java))}
    }

    private fun ChangeActivity(activityClass: Class<*>)
    {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
