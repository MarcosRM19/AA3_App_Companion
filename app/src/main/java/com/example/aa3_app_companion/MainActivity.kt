package com.example.aa3_app_companion


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var  splashScreenButton : Button
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var database: DatabaseReference
    // Initialize Google Identity Services (GIS) Client
    private lateinit var googleSignInClient: SignInClient
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Init Firebase analytics
        analytics = FirebaseAnalytics.getInstance(this)

        // Init Firebase database
        val databaseUrl = "https://app-companion-elden-ring-default-rtdb.europe-west1.firebasedatabase.app"
        database = FirebaseDatabase.getInstance(databaseUrl).getReference("messages")
        database.addChildEventListener(createChildEventListener())

        // Initialize SignInClient
        SignInClient = Identity.getSignInClient(this)
        googleSignInClient = Identity.getSignInClient(this)

        // Set up the sign-in request
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("YOUR_SERVER_CLIENT_ID")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("944638528163-j7qlgh0fss960hvu5um6eo53ob069s3g.apps.googleusercontent.com")
            .requestEmail()
            .build()


        // Register Activity Result Launcher
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val credential = googleSignInClient.getSignInCredentialFromIntent(result.data)
                    val idToken = credential.googleIdToken
                    val email = credential.id
                    Log.d("SignIn", "ID Token: $idToken, Email: $email")
                } catch (e: ApiException) {
                    Log.e("SignIn", "Sign-in failed: ${e.localizedMessage}", e)
                }
            } else {
                Log.e("SignIn", "Sign-in canceled or failed")
            }
        }

        // Set up the Google Sign-In button
        findViewById<Button>(R.id.google_sign_in_button).setOnClickListener {
            googleSignInClient.beginSignIn(signInRequest)
                .addOnSuccessListener { result ->
                    googleSignInLauncher.launch(result.pendingIntent.intent)
                }
                .addOnFailureListener { e ->
                    Log.e("SignIn", "Sign-in initiation failed: ${e.localizedMessage}")
                }
        }

        splashScreenButton = findViewById(R.id.splashScreenButton)
        splashScreenButton.setOnClickListener {onButtonClick()}

    }

    private fun createChildEventListener(): ChildEventListener{
        return object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user = snapshot.child("user").getValue(String::class.java)
                val message = snapshot.child("message").getValue(String::class.java)

                Log.d("Firebase test", "Added - user: $user, Message: $message")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val newUser = snapshot.child("user").getValue(String::class.java)
                val newMessage = snapshot.child("message").getValue(String::class.java)

                val oldSnapshot = previousChildName?.let { database.child(it).get().result }
                val oldUser = oldSnapshot?.child("user")?.getValue(String::class.java)
                val oldMessage = oldSnapshot?.child("message")?.getValue(String::class.java)

                Log.d("Firebase test", "changed - old User: $oldUser, old Message: $oldMessage")
                Log.d("Firebase test", "Changed - New User: $newUser, New Message: $newMessage")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val user = snapshot.child("user").getValue(String::class.java)
                val message = snapshot.child("message").getValue(String::class.java)

                Log.d("Firebase test", "Removed - user: $user, Message: $message")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                val movedKey = snapshot.key

                Log.d("Firebase test", "Moved - From: $previousChildName, To: $movedKey")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase test", "Cancelled - Error: ${error.message}")
            }
        }
    }

    private fun onButtonClick()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}