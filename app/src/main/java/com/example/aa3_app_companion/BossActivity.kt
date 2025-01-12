package com.example.aa3_app_companion

import EldenRingApi.EldenRingApiInstance
import EldenRingApi.EldenRingApiService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import models.Boss
import models.BossesResponse
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.analytics.FirebaseAnalytics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class BossActivity : AppCompatActivity() {

    private lateinit var apiService: EldenRingApiService

    private lateinit var classesButton: ImageButton
    private lateinit var itemButton: ImageButton
    private lateinit var mapButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var profileButton: ImageButton

    private lateinit var toolbar: Toolbar

    private lateinit var bossImages: Array<ImageView?>
    private lateinit var bossName: Array<TextView?>
    private lateinit var bossDescription: Array<TextView?>
    private lateinit var bossLocation: Array<TextView?>

    private lateinit var analytics : FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boss)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        InitButtons()
        InitImage()
        InitText()

        val bossIds = listOf(
            "Crucible Knight",
            "Tree Sentinel",
            "Beast Of Farum Azula",
            "Night's Cavalry",
            "Bloodhound Knight Darriwil",
            "Flying Dragon Agheel",
            "Ulcerated Tree Spirit",
            "Grave Warden Duelist",
            "Mad Pumpkin Head",
            "Guardian Golem",
            "Tibia Mariner",
            "Deathbird",
            "Grafted Scion",
            "Godrick the Grafted"
        )

        analytics = FirebaseAnalytics.getInstance(this)
        analytics.logEvent("OpenBossActivity", null)

        ButtonsLogic()
        classesData(bossIds)
    }

    private fun classesData(names: List<String>) {
        names.forEach { name ->
            EldenRingApiInstance.apiService.getBossesByName(name).enqueue(object : Callback<BossesResponse> {
                override fun onResponse(call: Call<BossesResponse>, response: Response<BossesResponse>) {
                    if (response.isSuccessful) {
                        val boss = response.body()?.data?.firstOrNull()
                        if (boss != null) {
                            val bossIndex = names.indexOf(name) + 1
                            loadBossData(boss, bossIndex)
                        } else {
                            Log.w("BossActivity", "Boss no encontrado para el nombre: $name")
                        }
                    } else {
                        Log.e("BossActivity", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BossesResponse>, t: Throwable) {
                    Log.e("BossActivity", "Error en la conexión: ${t.message}")
                }
            })
        }
    }

    private fun loadBossData(boss: Boss, bossNumber: Int) {
        if (bossNumber in 1..14) {
            val imageView = bossImages[bossNumber - 1]

            if (imageView != null) {
                boss.image?.let { loadImageFromUrl(it, imageView) }
            } else {
                Log.e("BossActivity", "ImageView no inicializado para el número de boss: $bossNumber")
            }

            bossName[bossNumber - 1]?.text = boss.name ?: "Desconocido"
            bossDescription[bossNumber - 1]?.text = boss.description ?: "Descripción no disponible"
            bossLocation[bossNumber - 1]?.text = boss.location ?: "Ubicación desconocida"
        } else {
            Log.w("BossActivity", "Número de boss fuera de rango: $bossNumber")
        }
    }

    private fun loadImageFromUrl(imageUrl: String?, imageView: ImageView) {
        if (imageUrl.isNullOrEmpty()) {
            Log.e("BossActivity", "URL de imagen no válida: $imageUrl")
            return
        }

        Thread {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                connection.connect()

                val inputStream: InputStream = connection.inputStream
                val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

                if (bitmap != null) {
                    Handler(Looper.getMainLooper()).post {
                        imageView.setImageBitmap(bitmap)
                    }
                } else {
                    Log.e("BossActivity", "El bitmap descargado es nulo. URL: $imageUrl")
                }

                connection.disconnect()
            } catch (e: Exception) {
                Log.e("BossActivity", "Error cargando imagen: ${e.localizedMessage}", e)
            }
        }.start()
    }

    private fun InitImage() {

        bossImages = arrayOfNulls(14)

        bossImages[0] = findViewById(R.id.boss1Image)
        bossImages[1] = findViewById(R.id.boss2Image)
        bossImages[2] = findViewById(R.id.boss3Image)
        bossImages[3] = findViewById(R.id.boss4Image)
        bossImages[4] = findViewById(R.id.boss5Image)
        bossImages[5] = findViewById(R.id.boss6Image)
        bossImages[6] = findViewById(R.id.boss7Image)
        bossImages[7] = findViewById(R.id.boss8Image)
        bossImages[8] = findViewById(R.id.boss9Image)
        bossImages[9] = findViewById(R.id.boss10Image)
        bossImages[10] = findViewById(R.id.boss11Image)
        bossImages[11] = findViewById(R.id.boss12Image)
        bossImages[12] = findViewById(R.id.boss13Image)
        bossImages[13] = findViewById(R.id.boss14Image)
    }

    private fun InitText()
    {
        bossName = arrayOfNulls(14)
        bossDescription = arrayOfNulls(14)
        bossLocation = arrayOfNulls(14)

        bossName[0] = findViewById(R.id.boss1name)
        bossName[1] = findViewById(R.id.boss2name)
        bossName[2] = findViewById(R.id.boss3name)
        bossName[3] = findViewById(R.id.boss4name)
        bossName[4] = findViewById(R.id.boss5name)
        bossName[5] = findViewById(R.id.boss6name)
        bossName[6] = findViewById(R.id.boss7name)
        bossName[7] = findViewById(R.id.boss8name)
        bossName[8] = findViewById(R.id.boss9name)
        bossName[9] = findViewById(R.id.boss10name)
        bossName[10] = findViewById(R.id.boss11name)
        bossName[11] = findViewById(R.id.boss12name)
        bossName[12] = findViewById(R.id.boss13name)
        bossName[13] = findViewById(R.id.boss14name)

        bossDescription[0] = findViewById(R.id.boss1description)
        bossDescription[1] = findViewById(R.id.boss2description)
        bossDescription[2] = findViewById(R.id.boss3description)
        bossDescription[3] = findViewById(R.id.boss4description)
        bossDescription[4] = findViewById(R.id.boss5description)
        bossDescription[5] = findViewById(R.id.boss6description)
        bossDescription[6] = findViewById(R.id.boss7description)
        bossDescription[7] = findViewById(R.id.boss8description)
        bossDescription[8] = findViewById(R.id.boss9description)
        bossDescription[9] = findViewById(R.id.boss10description)
        bossDescription[10] = findViewById(R.id.boss11description)
        bossDescription[11] = findViewById(R.id.boss12description)
        bossDescription[12] = findViewById(R.id.boss13description)
        bossDescription[13] = findViewById(R.id.boss14description)

        bossLocation[0] = findViewById(R.id.boss1location)
        bossLocation[1] = findViewById(R.id.boss2location)
        bossLocation[2] = findViewById(R.id.boss3location)
        bossLocation[3] = findViewById(R.id.boss4location)
        bossLocation[4] = findViewById(R.id.boss5location)
        bossLocation[5] = findViewById(R.id.boss6location)
        bossLocation[6] = findViewById(R.id.boss7location)
        bossLocation[7] = findViewById(R.id.boss8location)
        bossLocation[8] = findViewById(R.id.boss9location)
        bossLocation[9] = findViewById(R.id.boss10location)
        bossLocation[10] = findViewById(R.id.boss11location)
        bossLocation[11] = findViewById(R.id.boss12location)
        bossLocation[12] = findViewById(R.id.boss13location)
        bossLocation[13] = findViewById(R.id.boss14location)

    }

    private fun InitButtons() {
        classesButton = findViewById(R.id.classes)
        itemButton = findViewById(R.id.item)
        mapButton = findViewById(R.id.map)
        chatButton = findViewById(R.id.chat)
        profileButton = findViewById(R.id.profile)
    }

    private fun ButtonsLogic() {
        classesButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
        itemButton.setOnClickListener { ChangeActivity(ItemActivity::class.java) }
        mapButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { ChangeActivity(ConversationActivity::class.java) }
        profileButton.setOnClickListener { ChangeActivity(ProfileActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
