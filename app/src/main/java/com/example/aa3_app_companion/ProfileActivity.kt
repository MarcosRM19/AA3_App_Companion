package com.example.aa3_app_companion

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import android.Manifest
import android.net.Uri
import android.widget.ImageView

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
    private lateinit var takePictureButton : Button
    private lateinit var profilePicture : ImageView
    private lateinit var selectPictureButton : Button
    private var  photoUri: Uri? = null
    private val CAMERAREQUESTCODE = 100
    private val GALLERYREQUESTCODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        val dataBaseUrl = "https://app-companion-elden-ring-default-rtdb.europe-west1.firebasedatabase.app/"
        database = FirebaseDatabase.getInstance(dataBaseUrl).getReference("users")
        database.addChildEventListener(createChildEventListener())

        initViews()
        buttonsLogic()

        showName()
    }

    private fun initViews() {
        classesButton = findViewById(R.id.classes)
        itemButton = findViewById(R.id.item)
        mapButton = findViewById(R.id.map)
        chatButton = findViewById(R.id.chat)
        bossButton = findViewById(R.id.boss)
        logOutButton = findViewById(R.id.logOutButton)
        takePictureButton = findViewById(R.id.takePictureButton)
        selectPictureButton = findViewById(R.id.selectPictureText)
        profilePicture = findViewById(R.id.profileImage)
        nameText = findViewById(R.id.nameText)
    }

    private fun buttonsLogic() {
        classesButton.setOnClickListener { changeActivity(ClassesActivity::class.java) }
        itemButton.setOnClickListener { changeActivity(ItemActivity::class.java) }
        mapButton.setOnClickListener { changeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { changeActivity(ConversationActivity::class.java) }
        bossButton.setOnClickListener { changeActivity(BossActivity::class.java) }
        logOutButton.setOnClickListener {
            auth.signOut()
            changeActivity(MainActivity::class.java)
        }
        takePictureButton.setOnClickListener{ openCamera() }
        selectPictureButton.setOnClickListener{ selectPicture() }
    }

    private fun changeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun showName() {
        val currentUser = auth.currentUser

        if(currentUser != null) {
            val email = currentUser.email

            val query : Query = database.orderByChild("email").equalTo(email)

            query.get()
                .addOnSuccessListener { snapshot ->
                    if(snapshot.exists()) {
                        for (dataSnapshot in snapshot.children) {
                            val username = dataSnapshot.child("username").getValue(String::class.java)
                            nameText.text = username
                            Log.d("Firebase Test", "Username: $username")
                        }
                    } else {
                        Log.d("Firebase Test", "No username found for email: $email")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Firebase Test", "Error getting username: $exception")
                }


        }
    }

    private fun openCamera() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(cameraIntent.resolveActivity(packageManager) != null) {
                startActivity(cameraIntent)
            } else {
                Toast.makeText(this, "There is no camera app", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERAREQUESTCODE)
        }
    }

    private fun selectPicture() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(galleryIntent, GALLERYREQUESTCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GALLERYREQUESTCODE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            if(imageUri != null) {
                profilePicture.setImageURI(imageUri)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERAREQUESTCODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun createChildEventListener(): ChildEventListener {
        return object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val newUsername = snapshot.child("username").getValue(String::class.java)
                nameText.text = newUsername
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase Test", "Cancelled - Error: $error")
            }
        }
    }
}