package com.example.aa3_app_companion

import EldenRingApi.EldenRingApiInstance
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Classes
import models.ClassesAdapter
import models.ClassesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassesActivity : AppCompatActivity(), ClassesAdapter.OnButtonClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mapButton: ImageButton
    private lateinit var itemButton: ImageButton
    private lateinit var bossButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var profileButton: ImageButton
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        InitButtons()

        recyclerView = findViewById(R.id.classes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        classesData()
        ButtonsLogic()
    }

    override fun onButtonClick(classes: Classes) {
        val intent = Intent(this, ClassActivity::class.java)
        intent.putExtra("CLASS_NAME", classes.name)
        intent.putExtra("CLASS_DESCRIPTION", classes.description)
        intent.putExtra("CLASS_IMAGE_URL", classes.image)

        intent.putExtra("CLASS_HP", classes.stats.vigor)
        intent.putExtra("CLASS_FP", classes.stats.faith)
        intent.putExtra("CLASS_STRENGTH", classes.stats.strength)
        intent.putExtra("CLASS_DEXTERITY", classes.stats.dexterity)
        intent.putExtra("CLASS_INTELLIGENCE", classes.stats.intelligence)
        intent.putExtra("CLASS_FAITH", classes.stats.faith)
        intent.putExtra("CLASS_ENDURANCE", classes.stats.endurance)
        intent.putExtra("CLASS_ARCANE", classes.stats.arcane)

        startActivity(intent)
    }

    private fun classesData() {
        EldenRingApiInstance.apiService.getClasses(limit = 20).enqueue(object : Callback<ClassesResponse> {
            override fun onResponse(call: Call<ClassesResponse>, response: Response<ClassesResponse>) {
                if (response.isSuccessful) {
                    // Accedemos a la lista de clases dentro de la propiedad `data` de la respuesta
                    val classesList = response.body()?.data ?: emptyList()
                    recyclerView.adapter = ClassesAdapter(classesList, this@ClassesActivity)
                } else {
                    Toast.makeText(this@ClassesActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ClassesResponse>, t: Throwable) {
                Toast.makeText(this@ClassesActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun InitButtons() {
        mapButton = findViewById(R.id.map)
        itemButton = findViewById(R.id.item)
        bossButton = findViewById(R.id.boss)
        chatButton = findViewById(R.id.chat)
        profileButton = findViewById(R.id.profile)
    }

    private fun ButtonsLogic() {
        mapButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        itemButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        bossButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        profileButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
